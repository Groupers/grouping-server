package com.covengers.grouping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CheckUserWithEmailAndPhoneNumberRequestVo {
    private final String email;
    private final String phoneNumber;
}
