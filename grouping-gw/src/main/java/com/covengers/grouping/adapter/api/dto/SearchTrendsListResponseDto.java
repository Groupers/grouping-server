package com.covengers.grouping.adapter.api.dto;

import com.covengers.grouping.dto.KeywordResponseDto;
import com.covengers.grouping.vo.SearchTrendsListResponseVo;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchTrendsListResponseDto {
    private List<KeywordResponseDto> searchTrendsList;

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

    public SearchTrendsListResponseVo toVo() {
        return SearchTrendsListResponseVo.builder()
                .searchTrendsList(
                        searchTrendsList
                                .stream()
                                .map(keywordResponseDto -> keywordResponseDto.toVo())
                                .collect(Collectors.toList()))
                .build();
    }
}
