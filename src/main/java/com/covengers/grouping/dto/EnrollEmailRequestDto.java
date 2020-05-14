package com.covengers.grouping.dto;

import com.covengers.grouping.dto.vo.EnrollEmailRequestVo;

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
public class EnrollEmailRequestDto {
    String email;

    public EnrollEmailRequestVo toVo() {
        return EnrollEmailRequestVo.builder().email(email.toLowerCase()).build();
    }
}
