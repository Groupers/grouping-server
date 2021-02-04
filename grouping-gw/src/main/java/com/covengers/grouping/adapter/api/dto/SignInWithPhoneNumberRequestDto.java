package com.covengers.grouping.adapter.api.dto;

import com.covengers.grouping.vo.SignInWithPhoneNumberRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SignInWithPhoneNumberRequestDto {
    private final String phoneNumber;
    private final String password;

    public static SignInWithPhoneNumberRequestDto of(SignInWithPhoneNumberRequestVo vo) {
        return builder()
                .phoneNumber(vo.getPhoneNumber())
                .password(vo.getPassword())
                .build();
    }
}
