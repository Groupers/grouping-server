package com.covengers.grouping.dto;

import com.covengers.grouping.vo.ResetPasswordRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ResetPasswordRequestDto {
    private String password;

    public ResetPasswordRequestVo toVo() {
        return ResetPasswordRequestVo.builder()
                .password(password)
                .build();
    }
}
