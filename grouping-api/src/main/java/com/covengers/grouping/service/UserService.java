package com.covengers.grouping.service;

import java.util.Optional;

import com.covengers.grouping.component.GroupingUserRepositoryDecorator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.covengers.grouping.component.PhoneNationCodeClassifier;
import com.covengers.grouping.domain.GroupingUser;
import com.covengers.grouping.vo.CheckEmailResultVo;
import com.covengers.grouping.vo.CheckPhoneNumberResultVo;
import com.covengers.grouping.vo.FriendListResultVo;
import com.covengers.grouping.vo.GroupListResponseVo;
import com.covengers.grouping.vo.GroupingUserInfoVo;
import com.covengers.grouping.vo.GroupingUserVo;
import com.covengers.grouping.vo.PhoneNationCodeSeparationVo;
import com.covengers.grouping.vo.ResetPasswordRequestVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final GroupingUserRepositoryDecorator groupingUserRepository;
    private final PhoneNationCodeClassifier phoneNationCodeClassifier;

    public CheckEmailResultVo checkEmail(String email) {

        final Optional<GroupingUser> groupingUserOptional = groupingUserRepository.findTopByEmail(email);

        boolean isEmailAvailable = false;

        if (groupingUserOptional.isEmpty()) {
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

        if (groupingUserOptional.isEmpty()) {
            isPhoneNumberAvailable = true;
        }

        return CheckPhoneNumberResultVo.builder()
                                       .isPhoneNumberAvailable(isPhoneNumberAvailable)
                                       .build();
    }

    public GroupListResponseVo getGroupList(Long groupingUserId) {

        final GroupingUser groupingUser = groupingUserRepository.findTopById(groupingUserId);

        return GroupListResponseVo.builder()
                                  .groupList(groupingUser.toGroupList())
                                  .build();
    }

    public FriendListResultVo getFriendList(Long groupingUserId) {

        final GroupingUser groupingUser = groupingUserRepository.findTopById(groupingUserId);

        return FriendListResultVo.builder()
                                 .friendList(groupingUser.toFriendList())
                                 .build();
    }

    public GroupingUserVo getUserInfo(GroupingUserInfoVo groupingUserInfoVo) {

        final GroupingUser groupingUser = groupingUserRepository.findTopById(groupingUserInfoVo.getGroupingUserId());

        return groupingUser.toVo();
    }

    @Transactional
    public void resetPassword(Long groupingUserId, ResetPasswordRequestVo requestVo) {

        final GroupingUser groupingUser = groupingUserRepository.findTopById(groupingUserId);

        groupingUser.setPassword(requestVo.getPassword());

        groupingUserRepository.save(groupingUser);
    }

}
