package com.covengers.grouping.dto;

import com.covengers.grouping.vo.CheckEmailResultVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CheckEmailResponseDto {
    final boolean isEmailAvailable;

    public static CheckEmailResponseDto of(CheckEmailResultVo vo) {
        return builder()
                .isEmailAvailable(vo.isEmailAvailable())
                .build();
    }
}
