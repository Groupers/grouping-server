package com.covengers.grouping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ResetPasswordRequestVo {

    final String groupingUserId;
    final String newPassword;
}
