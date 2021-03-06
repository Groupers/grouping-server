package com.covengers.grouping.dto;

import com.covengers.grouping.vo.SearchTrendsListResultVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@Builder
public class SearchTrendsListResponseDto {
    private final List<KeywordDto> searchTrendsList;

    public static SearchTrendsListResponseDto of(SearchTrendsListResultVo vo) {
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
