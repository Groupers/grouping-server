package com.covengers.grouping.adapter.api.dto;

import com.covengers.grouping.vo.ResetPasswordRequestVo;
import com.covengers.grouping.vo.SignUpRequestVo;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordCompleteRequestDto {
    private String password;

    public static ResetPasswordCompleteRequestDto of(String password) {
        return builder()
                .password(password)
                .build();
    }
}
