package com.covengers.grouping.dto;

import java.util.Optional;

import com.covengers.grouping.dto.vo.CancelSignUpRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CancelSignUpRequestDto {
    private final String email;
    private final String phoneNumber;

    public CancelSignUpRequestVo toVo() {
        return CancelSignUpRequestVo.builder()
                                    .email(email != null ?
                                           Optional.of(email.toLowerCase()) :
                                           Optional.empty())
                                    .phoneNumber(phoneNumber != null ?
                                                 Optional.of(phoneNumber.toLowerCase()) :
                                                 Optional.empty())
                                    .build();
    }
}
