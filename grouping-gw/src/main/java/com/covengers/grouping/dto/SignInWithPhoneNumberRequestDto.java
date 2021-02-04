package com.covengers.grouping.dto;

import com.covengers.grouping.vo.SignInWithPhoneNumberRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SignInWithPhoneNumberRequestDto {
    private final String phoneNumber;
    private final String password;

    public SignInWithPhoneNumberRequestVo toVo() {
        return SignInWithPhoneNumberRequestVo.builder()
                                             .phoneNumber(phoneNumber)
                                             .password(password)
                                             .build();
    }
}
