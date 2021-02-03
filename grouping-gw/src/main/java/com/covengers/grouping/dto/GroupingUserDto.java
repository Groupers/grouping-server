package com.covengers.grouping.dto;

import java.time.LocalDate;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.constant.NationCode;
import com.covengers.grouping.constant.UserStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class GroupingUserDto {
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
}
