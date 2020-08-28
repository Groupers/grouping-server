package com.covengers.grouping.dto;

import com.covengers.grouping.vo.ResetPasswordRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ResetPasswordRequestDto {
    final String groupingUserId;
    final String newPassword;

    public ResetPasswordRequestVo toVo() {
        return ResetPasswordRequestVo.builder()
                .groupingUserId(groupingUserId)
                .newPassword(newPassword)
                .build();
    }
}
