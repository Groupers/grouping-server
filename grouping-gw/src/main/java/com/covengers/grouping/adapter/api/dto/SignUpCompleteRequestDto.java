package com.covengers.grouping.adapter.api.dto;

import java.time.LocalDate;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.vo.SignUpRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SignUpCompleteRequestDto {
    private final String email;
    private final String password;
    private final String name;
    private final Gender gender;
    private final LocalDate birthday;
    private final String phoneNumber;

    public static SignUpCompleteRequestDto of(SignUpRequestVo vo, String password) {
        return builder()
                .email(vo.getEmail())
                .password(password)
                .name(vo.getName())
                .gender(vo.getGender())
                .birthday(LocalDate.parse(vo.getBirthday().replaceAll("\\.", "-")))
                .phoneNumber(vo.getPhoneNumber())
                .build();
    }
}
