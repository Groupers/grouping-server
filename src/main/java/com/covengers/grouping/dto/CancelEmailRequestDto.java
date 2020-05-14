package com.covengers.grouping.dto;

import com.covengers.grouping.dto.vo.CancelEmailRequestVo;

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
public class CancelEmailRequestDto {
    private String email;

    public CancelEmailRequestVo toVo() {
        return CancelEmailRequestVo.builder().email(email.toLowerCase()).build();
    }
}
