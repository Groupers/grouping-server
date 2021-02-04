package com.covengers.grouping.dto;

import com.covengers.grouping.vo.SearchTrendsListResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@Builder
public class SearchTrendsListResponseDto {
    private final List<KeywordResponseDto> searchTrendsList;

    public static SearchTrendsListResponseDto of(SearchTrendsListResponseVo vo) {
        return builder()
                .searchTrendsList(
                        vo.getSearchTrendsList()
                                .stream()
                                .map(keywordVo -> KeywordResponseDto.of(keywordVo))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
