package com.covengers.grouping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SignInWithEmailRequestVo {
    private final String email;
    private final String password;
}