package com.covengers.grouping.vo;

import com.covengers.grouping.constant.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@Getter
@ToString
public class SignUpRequestVo {
    private final String email;
    private final String password;
    private final String name;
    private final Gender gender;
    private final LocalDate birthday;
    private final String phoneNumber;
}
