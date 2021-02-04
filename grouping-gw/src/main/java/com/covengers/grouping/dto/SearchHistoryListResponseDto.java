package com.covengers.grouping.dto;

import com.covengers.grouping.vo.SearchHistoryListResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@Builder
public class SearchHistoryListResponseDto {
    private final List<KeywordResponseDto> searchHistoryList;

    public static SearchHistoryListResponseDto of(SearchHistoryListResponseVo vo) {
        return builder()
                .searchHistoryList(
                        vo.getSearchHistoryList()
                                .stream()
                                .map(keywordVo -> KeywordResponseDto.of(keywordVo))
                                .collect(Collectors.toList())
                )
                .build();
    }

    public SearchHistoryListResponseVo toVo() {
        return SearchHistoryListResponseVo.builder()
                .searchHistoryList(
                        searchHistoryList
                                .stream()
                                .map(keywordResponseDto -> keywordResponseDto.toVo())
                                .collect(Collectors.toList()))
                .build();
    }
}
