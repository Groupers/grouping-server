package com.covengers.grouping.dto;

import java.time.LocalDate;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.dto.vo.SignUpRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SignUpRequestDto {
    private final String email;
    private final String password;
    private final String name;
    private final Gender gender;
    private final LocalDate birthday;
    private final String phoneNumber;

    public SignUpRequestVo toVo() {
        return SignUpRequestVo.builder()
                              .email(email)
                              .password(password)
                              .name(name)
                              .gender(gender)
                              .birthday(birthday)
                              .phoneNumber(phoneNumber)
                              .build();
    }
}
