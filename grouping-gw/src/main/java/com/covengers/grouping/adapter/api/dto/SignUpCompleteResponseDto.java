package com.covengers.grouping.adapter.api.dto;

import java.time.LocalDate;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.constant.NationCode;
import com.covengers.grouping.constant.UserStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SignUpCompleteResponseDto {
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
