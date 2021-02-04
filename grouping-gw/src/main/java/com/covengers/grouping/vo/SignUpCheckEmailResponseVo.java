package com.covengers.grouping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SignUpCheckEmailResponseVo {
    private final boolean isEmailAvailable;
}
