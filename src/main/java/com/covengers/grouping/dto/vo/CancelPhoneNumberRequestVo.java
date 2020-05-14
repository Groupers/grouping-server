package com.covengers.grouping.dto.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CancelPhoneNumberRequestVo {
    private final String phoneNumber;
}
