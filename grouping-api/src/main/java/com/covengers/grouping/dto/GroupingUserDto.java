package com.covengers.grouping.dto;

import java.time.LocalDate;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.constant.NationCode;
import com.covengers.grouping.constant.UserStatus;
import com.covengers.grouping.vo.GroupingUserVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class GroupingUserDto {
    private final String groupingUserId;
    private final UserStatus userStatus;
    private final String email;
    private final NationCode nationCode;
    private final String phoneNumber;
    private final String name;
    private final String userId;
    private final Gender gender;
    private final LocalDate birthday;
    private final String representProfileImage;

    public static GroupingUserDto of(GroupingUserVo vo) {
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
                .build();
    }
}
