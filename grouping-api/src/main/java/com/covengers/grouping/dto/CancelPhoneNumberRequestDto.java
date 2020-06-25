package com.covengers.grouping.dto;

import com.covengers.grouping.vo.CancelPhoneNumberRequestVo;

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
public class CancelPhoneNumberRequestDto {
    private String phoneNumber;

    public CancelPhoneNumberRequestVo toVo() {
        return CancelPhoneNumberRequestVo.builder().phoneNumber(phoneNumber.toLowerCase()).build();
    }
}
