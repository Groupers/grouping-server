package com.covengers.grouping.vo;

import com.covengers.grouping.domain.Keyword;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
public class SearchListResultVo {
    private final List<KeywordVo> searchList;
}
