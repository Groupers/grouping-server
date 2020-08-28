package com.covengers.grouping.dto;

import com.covengers.grouping.vo.CheckUserWithEmailAndPhoneNumberRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CheckUserWithEmailAndPhoneNumberRequestDto {
    private final String email;
    private final String phoneNumber;

    public CheckUserWithEmailAndPhoneNumberRequestVo toVo() {
        return CheckUserWithEmailAndPhoneNumberRequestVo
                .builder()
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
