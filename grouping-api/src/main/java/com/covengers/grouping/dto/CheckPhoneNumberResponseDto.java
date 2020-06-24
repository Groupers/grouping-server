package com.covengers.grouping.dto;

import com.covengers.grouping.vo.CheckPhoneNumberResultVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CheckPhoneNumberResponseDto {
    final boolean isPhoneNumberAvailable;

    public static CheckPhoneNumberResponseDto of(CheckPhoneNumberResultVo vo) {
        return builder()
                .isPhoneNumberAvailable(vo.isPhoneNumberAvailable())
                .build();
    }
}
