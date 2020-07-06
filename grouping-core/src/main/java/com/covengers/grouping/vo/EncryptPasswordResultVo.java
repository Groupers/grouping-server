package com.covengers.grouping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class EncryptPasswordResultVo {
    private final String encrytedPassword;
}
