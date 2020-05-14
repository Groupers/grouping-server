package com.covengers.grouping.dto.vo;

import java.util.Optional;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class CancelSignUpRequestVo {
    private final Optional<String> email;
    private final Optional<String> phoneNumber;
}
