package com.covengers.grouping.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.domain.GroupingUser;
import com.covengers.grouping.dto.CheckEmailResponseDto;
import com.covengers.grouping.dto.vo.CheckEmailResultVo;
import com.covengers.grouping.dto.vo.CheckPhoneNumberResultVo;
import com.covengers.grouping.dto.vo.CheckUserIdResultVo;
import com.covengers.grouping.dto.vo.EnrollEmailRequestVo;
import com.covengers.grouping.dto.vo.GroupingUserVo;
import com.covengers.grouping.exception.CommonException;
import com.covengers.grouping.repository.GroupingUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final GroupingUserRepository groupingUserRepository;

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

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopByPhoneNumber(phoneNumber);
        return CheckPhoneNumberResultVo.builder()
                                       .isPhoneNumberAvailable(!groupingUserOptional.isPresent())
                                       .build();
    }

    @Transactional
    public GroupingUserVo enrollEmail(EnrollEmailRequestVo requestVo) {

        final boolean isEnrollEmailAvailable = !checkEmail(requestVo.getEmail()).isEmailAvailable();

        if (!isEnrollEmailAvailable) {
            throw new CommonException(ResponseCode.EMAIL_ALREADY_EXISTED);
        }

        final GroupingUser groupingUser = new GroupingUser(requestVo.getEmail());
        groupingUserRepository.save(groupingUser);
        return groupingUser.toVo();
    }
}
