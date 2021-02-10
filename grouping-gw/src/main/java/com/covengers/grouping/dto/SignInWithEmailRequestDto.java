package com.covengers.grouping.dto;

import com.covengers.grouping.vo.SignInWithEmailRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SignInWithEmailRequestDto {
    private final String email;
    private final String password;

    public SignInWithEmailRequestVo toVo() {
        return SignInWithEmailRequestVo.builder()
                .email(email)
                .password(password)
                .build();
    }
}