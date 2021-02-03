package com.covengers.grouping.adapter.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SignUpCheckPhoneNumberResponseDto {
    private final boolean isPhoneNumberAvailable;
}
