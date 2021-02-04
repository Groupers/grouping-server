package com.covengers.grouping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SignUpCheckPhoneNumberResponseVo {
    private final boolean isPhoneNumberAvailable;
}
