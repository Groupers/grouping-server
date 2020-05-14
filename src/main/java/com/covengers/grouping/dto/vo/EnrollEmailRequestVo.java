package com.covengers.grouping.dto.vo;

import java.util.Optional;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class EnrollEmailRequestVo {
    private final Optional<String> id;
    private final String email;
}
