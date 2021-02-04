package com.covengers.grouping.adapter.api.dto;

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

    public static SignInWithEmailRequestDto of(SignInWithEmailRequestVo vo) {
        return builder()
                .email(vo.getEmail())
                .password(vo.getPassword())
                .build();
    }
}
