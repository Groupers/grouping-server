package com.covengers.grouping.adapter.api.dto;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.constant.NationCode;
import com.covengers.grouping.constant.UserStatus;
import com.covengers.grouping.vo.GroupResponseVo;
import com.covengers.grouping.vo.GroupingUserResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Builder
@ToString
public class GroupingUserResponseDto {
    private final Long groupingUserId;
    private final UserStatus userStatus;
    private final String email;
    private final NationCode nationCode;
    private final String phoneNumber;
    private final String name;
    private final String userId;
    private final Gender gender;
    private final LocalDate birthday;
    private final String representProfileImage;
    private final String password;

    public static GroupingUserResponseDto of(GroupingUserResponseVo vo) {
        return builder()
                .groupingUserId(vo.getGroupingUserId())
                .userStatus(vo.getUserStatus())
                .email(vo.getEmail())
                .nationCode(vo.getNationCode())
                .phoneNumber(vo.getPhoneNumber())
                .name(vo.getName())
                .userId(vo.getUserId())
                .gender(vo.getGender())
                .birthday(vo.getBirthday())
                .representProfileImage(vo.getRepresentProfileImage())
                .password(vo.getPassword())
                .build();
    }

    public GroupingUserResponseVo toVo() {
        return GroupingUserResponseVo.builder()
                .groupingUserId(groupingUserId)
                .userStatus(userStatus)
                .email(email)
                .nationCode(nationCode)
                .phoneNumber(phoneNumber)
                .name(name)
                .userId(userId)
                .gender(gender)
                .birthday(birthday)
                .representProfileImage(representProfileImage)
                .password(password)
                .build();
    }
}
