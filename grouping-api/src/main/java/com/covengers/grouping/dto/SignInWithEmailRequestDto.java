package com.covengers.grouping.dto;

import com.covengers.grouping.vo.SignInWithEmailRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SignInWithEmailRequestDto {
    private final String email;
    private final String password;

    public SignInWithEmailRequestVo toVo() {
        return SignInWithEmailRequestVo.builder().email(email.toLowerCase()).password(password).build();
    }
}
