package com.covengers.grouping.dto;

import com.covengers.grouping.vo.SignInEmailRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SignInEmailRequestDto {
    private final String email;
    private final String password;

    public SignInEmailRequestVo toVo() {
        return SignInEmailRequestVo.builder().email(email.toLowerCase()).password(password).build();
    }
}
