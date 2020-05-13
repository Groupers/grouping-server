package com.covengers.grouping.dto;

import com.covengers.grouping.dto.vo.EnrollPhoneNumberRequestVo;

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
public class EnrollPhoneNumberRequestDto {
    String phoneNumber;
    String id;

    public EnrollPhoneNumberRequestVo toVo() {
        return EnrollPhoneNumberRequestVo.builder()
                                         .id(id)
                                         .phoneNumber(phoneNumber)
                                         .build();
    }
}
