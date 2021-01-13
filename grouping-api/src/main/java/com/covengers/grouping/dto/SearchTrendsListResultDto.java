package com.covengers.grouping.dto;

import com.covengers.grouping.vo.SearchHistoryListResultVo;
import com.covengers.grouping.vo.SearchTrendsListResultVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@Builder
public class SearchTrendsListResultDto {
    private final List<KeywordDto> searchTrendsList;

    public static SearchTrendsListResultDto of(SearchTrendsListResultVo vo) {
        return builder()
                .searchTrendsList(
                        vo.getSearchTrendsList()
                                .stream()
                                .map(keywordVo -> KeywordDto.of(keywordVo))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
