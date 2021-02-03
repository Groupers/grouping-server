package com.covengers.grouping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class JwtTokenVo {
    private final String accessToken;
    private final String tokenType = "Bearer";
}
