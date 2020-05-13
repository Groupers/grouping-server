package com.covengers.grouping.dto.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class EnrollPhoneNumberRequestVo {
    private final String id;
    private final String phoneNumber;
}
