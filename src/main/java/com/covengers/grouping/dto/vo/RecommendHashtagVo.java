package com.covengers.grouping.dto.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
public class RecommendHashtagVo {
    private final List<String> hashtagList;
}
