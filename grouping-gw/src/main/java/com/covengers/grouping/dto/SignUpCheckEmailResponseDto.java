package com.covengers.grouping.dto;

import com.covengers.grouping.vo.SignUpCheckEmailResponseVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SignUpCheckEmailResponseDto {
    private final boolean isEmailAvailable;

    public static SignUpCheckEmailResponseDto of(SignUpCheckEmailResponseVo vo) {
        return builder()
                .isEmailAvailable(vo.isEmailAvailable())
                .build();
    }
}
