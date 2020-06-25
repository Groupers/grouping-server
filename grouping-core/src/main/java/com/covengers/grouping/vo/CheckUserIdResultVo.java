package com.covengers.grouping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class CheckUserIdResultVo {
    final boolean isUserIdAvailable;
}
