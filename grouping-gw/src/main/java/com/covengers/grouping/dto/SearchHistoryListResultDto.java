package com.covengers.grouping.dto;

import com.covengers.grouping.vo.RecommendGroupVo;
import com.covengers.grouping.vo.SearchHistoryListResultVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@Builder
public class SearchHistoryListResultDto {
    private final List<KeywordDto> searchHistoryList;

    public static SearchHistoryListResultDto of(SearchHistoryListResultVo vo) {
        return builder()
                .searchHistoryList(
                        vo.getSearchHistoryList()
                                .stream()
                                .map(keywordVo -> KeywordDto.of(keywordVo))
                                .collect(Collectors.toList())
                )
                .build();
    }

    public SearchHistoryListResultVo toVo() {
        return SearchHistoryListResultVo.builder()
                .searchHistoryList(
                        searchHistoryList
                                .stream()
                                .map(keywordDto -> keywordDto.toVo())
                                .collect(Collectors.toList()))
                .build();
    }
}
