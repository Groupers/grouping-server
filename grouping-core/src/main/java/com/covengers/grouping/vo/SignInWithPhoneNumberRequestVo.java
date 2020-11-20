package com.covengers.grouping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class SignInWithPhoneNumberRequestVo {

    private final String phoneNumber;
    private final String password;
}
