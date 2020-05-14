package com.covengers.grouping.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CancelSignUpRequestDto {
    final private String id;
}
