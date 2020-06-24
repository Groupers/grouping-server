package com.covengers.grouping.dto;

import com.covengers.grouping.vo.CheckUserIdResultVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CheckUserIdResponseDto {
    final boolean isUserIdAvailable;

    public static CheckUserIdResponseDto of(CheckUserIdResultVo vo) {
        return builder()
                .isUserIdAvailable(vo.isUserIdAvailable())
                .build();
    }
}
