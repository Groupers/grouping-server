package com.covengers.grouping.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.covengers.grouping.component.PhoneNationCodeClassifier;
import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.domain.GroupingUser;
import com.covengers.grouping.exception.CommonException;
import com.covengers.grouping.repository.GroupingUserRepository;
import com.covengers.grouping.vo.CheckEmailResultVo;
import com.covengers.grouping.vo.CheckPhoneNumberResultVo;
import com.covengers.grouping.vo.FriendListResultVo;
import com.covengers.grouping.vo.GroupListResponseVo;
import com.covengers.grouping.vo.GroupingUserVo;
import com.covengers.grouping.vo.PhoneNationCodeSeparationVo;
import com.covengers.grouping.vo.ResetPasswordRequestVo;

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

        boolean isEmailAvailable = false;

        if (!groupingUserOptional.isPresent()) {
            isEmailAvailable = true;
        }

        return CheckEmailResultVo.builder()
                                 .isEmailAvailable(isEmailAvailable)
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

    public GroupListResponseVo getGroupList(Long groupingUserId) {

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopById(groupingUserId);

        if (!groupingUserOptional.isPresent()) {
            throw new CommonException(ResponseCode.USER_NOT_EXISTED);
        }

        return GroupListResponseVo.builder()
                                  .groupList(groupingUserOptional.get().toGroupList())
                                  .build();
    }

    public FriendListResultVo getFriendList(Long groupingUserId) {

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopById(groupingUserId);

        if (!groupingUserOptional.isPresent()) {
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
    public void resetPassword(Long groupingUserId, ResetPasswordRequestVo requestVo) {

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopById(groupingUserId);

        final GroupingUser groupingUser =
                groupingUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        groupingUser.setPassword(requestVo.getPassword());

        groupingUserRepository.save(groupingUser);
    }

}
