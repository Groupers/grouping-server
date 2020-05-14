package com.covengers.grouping.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.covengers.grouping.component.PhoneNationCodeClassifier;
import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.domain.GroupingUser;
import com.covengers.grouping.dto.vo.CheckEmailResultVo;
import com.covengers.grouping.dto.vo.CheckPhoneNumberResultVo;
import com.covengers.grouping.dto.vo.CheckUserIdResultVo;
import com.covengers.grouping.dto.vo.EnrollEmailRequestVo;
import com.covengers.grouping.dto.vo.EnrollPhoneNumberRequestVo;
import com.covengers.grouping.dto.vo.GroupingUserVo;
import com.covengers.grouping.dto.vo.PhoneNationCodeSeparationVo;
import com.covengers.grouping.exception.CommonException;
import com.covengers.grouping.repository.GroupingUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final GroupingUserRepository groupingUserRepository;
    private final PhoneNationCodeClassifier phoneNationCodeClassifier;

    public CheckEmailResultVo checkEmail(String email) {

        final Optional<GroupingUser> groupingUserOptional = groupingUserRepository.findTopByEmail(email);
        return CheckEmailResultVo.builder()
                                 .isEmailAvailable(!groupingUserOptional.isPresent())
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

        return CheckPhoneNumberResultVo.builder()
                                       .isPhoneNumberAvailable(!groupingUserOptional.isPresent())
                                       .build();
    }

    @Transactional
    public GroupingUserVo enrollEmail(EnrollEmailRequestVo requestVo) {

        final Optional<String> idOptional = requestVo.getId();

        if (idOptional.isPresent()) {
            final Optional<GroupingUser> groupingUserOptional =
                    groupingUserRepository.findById(idOptional.get());

            if (groupingUserOptional.isPresent()) {
                final GroupingUser groupingUser = groupingUserOptional.get();
                groupingUser.updateEmail(requestVo.getEmail());
                groupingUserRepository.save(groupingUser);
                return groupingUser.toVo();
            }
        }

        final boolean isEnrollEmailAvailable = checkEmail(requestVo.getEmail()).isEmailAvailable();

        if (!isEnrollEmailAvailable) {
            throw new CommonException(ResponseCode.EMAIL_ALREADY_EXISTED);
        }

        final GroupingUser groupingUser = new GroupingUser(requestVo.getEmail());
        groupingUserRepository.save(groupingUser);
        return groupingUser.toVo();
    }

    @Transactional
    public GroupingUserVo enrollPhoneNumber(EnrollPhoneNumberRequestVo requestVo) {

        final boolean isEnrollPhoneNumberAvailable =
                checkPhoneNumber(requestVo.getPhoneNumber()).isPhoneNumberAvailable();

        if (!isEnrollPhoneNumberAvailable) {
            throw new CommonException(ResponseCode.PHONE_NUMBER_ALREADY_EXISTED);
        }

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findById(requestVo.getId());

        final GroupingUser groupingUser =
                groupingUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        final PhoneNationCodeSeparationVo phoneNationCodeSeparationVo =
                phoneNationCodeClassifier.separate(requestVo.getPhoneNumber());

        groupingUser.updatePhoneInfo(phoneNationCodeSeparationVo.getPurePhoneNumber(),
                                     phoneNationCodeSeparationVo.getNationCode());

        groupingUserRepository.save(groupingUser);
        return groupingUser.toVo();
    }

    @Transactional
    public void cancelSignUp(String id) {
        final Optional<GroupingUser> groupingUserOptional = groupingUserRepository.findById(id);

        final GroupingUser groupingUser =
                groupingUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        groupingUserRepository.delete(groupingUser);
    }
}
