package com.covengers.grouping.dto;

import com.covengers.grouping.vo.SignUpCheckPhoneNumberResponseVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SignUpCheckPhoneNumberResponseDto {
    private final boolean isPhoneNumberAvailable;

    public static SignUpCheckPhoneNumberResponseDto of(SignUpCheckPhoneNumberResponseVo vo) {
        return builder()
                .isPhoneNumberAvailable(vo.isPhoneNumberAvailable())
                .build();
    }
}
