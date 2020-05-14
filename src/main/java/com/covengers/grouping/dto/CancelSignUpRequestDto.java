package com.covengers.grouping.dto;

import java.util.Optional;

import com.covengers.grouping.dto.vo.CancelSignUpRequestVo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelSignUpRequestDto {
    private String email;
    private String phoneNumber;

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
