package com.covengers.grouping.vo;

import com.covengers.grouping.constant.NationCode;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PhoneNationCodeSeparationVo {
    private final NationCode nationCode;
    private final String purePhoneNumber;
}
