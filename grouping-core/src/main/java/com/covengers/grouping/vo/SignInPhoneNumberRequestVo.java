package com.covengers.grouping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignInPhoneNumberRequestVo {

    private final String phoneNumber;
    private final String password;
}
