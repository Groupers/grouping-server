package com.covengers.grouping.dto;

import java.util.Optional;

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
    String id;

    public EnrollEmailRequestVo toVo() {
        return EnrollEmailRequestVo.builder().id(Optional.of(id)).email(email).build();
    }
}
