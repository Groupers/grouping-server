package com.covengers.grouping.service;

import com.covengers.grouping.component.PasswordShaEncryptor;
import com.covengers.grouping.component.PhoneNationCodeClassifier;
import com.covengers.grouping.constant.RedisCacheTime;
import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.domain.GroupingUser;
import com.covengers.grouping.exception.CommonException;
import com.covengers.grouping.repository.GroupingUserRepository;
import com.covengers.grouping.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final GroupingUserRepository groupingUserRepository;
    private final PhoneNationCodeClassifier phoneNationCodeClassifier;
    private final PasswordShaEncryptor passwordShaEncryptor;
    private final RedisTemplate<String, String> redisTemplate;

    public CheckEmailResultVo checkEmail(String email) {

        final Optional<GroupingUser> groupingUserOptional = groupingUserRepository.findTopByEmail(email);

        boolean isEmailAvailable = false;

        if (!groupingUserOptional.isPresent()) {
            isEmailAvailable = true;
        }

        return CheckEmailResultVo.builder()
                                 .isEmailAvailable(isEmailAvailable)
                                 .build();
    }

    public CheckUserIdResultVo checkUserId(String userId) {

        final Optional<GroupingUser> groupingUserOptional = groupingUserRepository.findTopByUserId(userId);
        return CheckUserIdResultVo.builder()
                                  .isUserIdAvailable(!groupingUserOptional.isPresent())
                                  .build();
    }

    public CheckPhoneNumberResultVo checkPhoneNumber(String phoneNumber) {

        final PhoneNationCodeSeparationVo phoneNationCodeSeparationVo =
                phoneNationCodeClassifier.separate(phoneNumber);

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopByPhoneNumberAndNationCode(
                        phoneNationCodeSeparationVo.getPurePhoneNumber(),
                        phoneNationCodeSeparationVo.getNationCode());

        boolean isPhoneNumberAvailable = false;

        if (!groupingUserOptional.isPresent()) {
            isPhoneNumberAvailable = true;
        }

        return CheckPhoneNumberResultVo.builder()
                                       .isPhoneNumberAvailable(isPhoneNumberAvailable)
                                       .build();
    }

    public GroupListResponseVo getGroupList(String groupingUserId) {

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopById(groupingUserId);

        if (!groupingUserOptional.isPresent()) {
            throw new CommonException(ResponseCode.USER_NOT_EXISTED);
        }

        return GroupListResponseVo.builder()
                .groupList(groupingUserOptional.get().toGroupList())
                .build();
    }

    public FriendListResultVo getFriendList(String groupingUserId) {

        Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopById(groupingUserId);

        if(!groupingUserOptional.isPresent()) {
            throw new CommonException(ResponseCode.USER_NOT_EXISTED);
        }

        return FriendListResultVo.builder()
                .friendList(groupingUserOptional.get().toFriendList())
                .build();
    }

    public GroupingUserVo checkUserWithEmailAndPhoneNumber(String email, String phoneNumber) {

        final PhoneNationCodeSeparationVo phoneNationCodeSeparationVo =
                phoneNationCodeClassifier.separate(phoneNumber);

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopByEmailAndPhoneNumberAndNationCode(
                        email,
                        phoneNationCodeSeparationVo.getPurePhoneNumber(),
                        phoneNationCodeSeparationVo.getNationCode());

        final GroupingUser groupingUser =
                groupingUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        return groupingUser.toVo();
    }

    @Transactional
    public void enrollEmail(EnrollEmailRequestVo requestVo) {

        final boolean isEnrollEmailAvailable = checkEmail(requestVo.getEmail()).isEmailAvailable();

        if (!isEnrollEmailAvailable) {
            throw new CommonException(ResponseCode.EMAIL_ALREADY_EXISTED);
        }

        redisTemplate.opsForValue().set(requestVo.getEmail(), requestVo.getEmail());
        redisTemplate.expire(requestVo.getEmail(),
                             RedisCacheTime.SIGN_UP_EMAIL.getCacheTime(),
                             TimeUnit.MINUTES);
    }

    @Transactional
    public void enrollPhoneNumber(EnrollPhoneNumberRequestVo requestVo) {

        final boolean isEnrollPhoneNumberAvailable =
                checkPhoneNumber(requestVo.getPhoneNumber()).isPhoneNumberAvailable();

        if (!isEnrollPhoneNumberAvailable) {
            throw new CommonException(ResponseCode.PHONE_NUMBER_ALREADY_EXISTED);
        }

        redisTemplate.opsForValue().set(requestVo.getPhoneNumber(), requestVo.getPhoneNumber());
        redisTemplate.expire(requestVo.getPhoneNumber(),
                             RedisCacheTime.SIGN_UP_PHONE_NUMBER.getCacheTime(),
                             TimeUnit.MINUTES);
    }

    @Transactional
    public void cancelSignUp(CancelSignUpRequestVo requestVo) {
        requestVo.getEmail().ifPresent(redisTemplate::delete);
        requestVo.getPhoneNumber().ifPresent(redisTemplate::delete);
    }

    @Transactional
    public void cancelSignUpEmail(CancelEmailRequestVo requestVo) {
        redisTemplate.delete(requestVo.getEmail());
    }

    @Transactional
    public void cancelSignUpPhoneNumber(CancelPhoneNumberRequestVo requestVo) {
        redisTemplate.delete(requestVo.getPhoneNumber());
    }

    @Transactional
    public GroupingUserVo completeSignUp(SignUpRequestVo requestVo) {

        final boolean isValidEmail = !groupingUserRepository.findTopByEmail(requestVo.getEmail()).isPresent();

        final PhoneNationCodeSeparationVo phoneNationCodeSeparationVo =
                phoneNationCodeClassifier.separate(requestVo.getPhoneNumber());

        final String encryptPassword = passwordShaEncryptor.encrytPassword(requestVo.getPassword());

        final boolean isValidPhoneNumber = !groupingUserRepository.findTopByPhoneNumberAndNationCode(
                        phoneNationCodeSeparationVo.getPurePhoneNumber(),
                        phoneNationCodeSeparationVo.getNationCode()).isPresent();

        if (!isValidEmail || !isValidPhoneNumber) {
            throw new CommonException(ResponseCode.SIGN_UP_FAILED_FOR_INVALID_INFO);
        }

        final GroupingUser groupingUser = new GroupingUser(requestVo.getEmail(),
                                                           encryptPassword,
                                                           requestVo.getName(),
                                                           requestVo.getGender(),
                                                           requestVo.getBirthday(),
                                                           phoneNationCodeSeparationVo.getPurePhoneNumber(),
                                                           phoneNationCodeSeparationVo.getNationCode());
        groupingUserRepository.save(groupingUser);

        return groupingUser.toVo();
    }

    @Transactional(readOnly = true)
    public GroupingUserVo signInWithEmail(SignInWithEmailRequestVo requestVo) {

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopByEmail(requestVo.getEmail());

        final GroupingUser groupingUser =
                groupingUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        final String encryptedPassword = passwordShaEncryptor.encrytPassword(requestVo.getPassword());

        if (!groupingUser.getPassword().equals(encryptedPassword)) {
            throw new CommonException(ResponseCode.INVALID_PASSWORD);
        }

        return groupingUser.toVo();
    }

    @Transactional(readOnly = true)
    public GroupingUserVo signInWithPhoneNumber(SignInWithPhoneNumberRequestVo requestVo) {

        final PhoneNationCodeSeparationVo phoneNationCodeSeparationVo =
                phoneNationCodeClassifier.separate(requestVo.getPhoneNumber());

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopByPhoneNumberAndNationCode(
                        phoneNationCodeSeparationVo.getPurePhoneNumber(),
                        phoneNationCodeSeparationVo.getNationCode());

        final GroupingUser groupingUser =
                groupingUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        final String encryptedPassword = passwordShaEncryptor.encrytPassword(requestVo.getPassword());

        if (!groupingUser.getPassword().equals(encryptedPassword)) {
            throw new CommonException(ResponseCode.INVALID_PASSWORD);
        }

        return groupingUser.toVo();
    }

    @Transactional
    public void resetPassword(String groupingUserId, ResetPasswordRequestVo requestVo) {

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopById(groupingUserId);

        final GroupingUser groupingUser =
                groupingUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        final String encryptedPassword = passwordShaEncryptor.encrytPassword(requestVo.getPassword());

        groupingUser.setPassword(encryptedPassword);

        groupingUserRepository.save(groupingUser);
    }
}
