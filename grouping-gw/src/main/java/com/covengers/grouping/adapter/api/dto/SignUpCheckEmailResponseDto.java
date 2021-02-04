package com.covengers.grouping.adapter.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SignUpCheckEmailResponseDto {
    private final boolean isEmailAvailable;
}
