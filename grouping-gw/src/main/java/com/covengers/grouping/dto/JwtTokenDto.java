package com.covengers.grouping.dto;

import com.covengers.grouping.vo.JwtTokenVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class JwtTokenDto {
    private final String accessToken;

    public static JwtTokenDto of(JwtTokenVo vo) {
        return builder()
                .accessToken(vo.getTokenType() + ' ' + vo.getAccessToken())
                .build();

    }
}
