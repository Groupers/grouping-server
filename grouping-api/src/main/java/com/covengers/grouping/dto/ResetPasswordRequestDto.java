package com.covengers.grouping.dto;

import com.covengers.grouping.vo.ResetPasswordRequestVo;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequestDto {
    private String password;

    public ResetPasswordRequestVo toVo() {
        return ResetPasswordRequestVo.builder()
                .password(password)
                .build();
    }
}
