package com.covengers.grouping.dto;

import com.covengers.grouping.vo.SearchHistoryListResultVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@Builder
public class SearchHistoryListResponseDto {
    private final List<KeywordDto> searchHistoryList;

    public static SearchHistoryListResponseDto of(SearchHistoryListResultVo vo) {
        return builder()
                .searchHistoryList(
                        vo.getSearchHistoryList()
                                .stream()
                                .map(keywordVo -> KeywordDto.of(keywordVo))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
