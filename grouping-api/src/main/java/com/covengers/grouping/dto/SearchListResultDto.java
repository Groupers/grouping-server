package com.covengers.grouping.dto;

import com.covengers.grouping.vo.KeywordVo;
import com.covengers.grouping.vo.SearchListResultVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
public class SearchListResultDto {
    private final List<KeywordVo> searchList;

    public static SearchListResultDto of(SearchListResultVo vo) {
        return builder()
                .searchList(vo.getSearchList())
                .build();
    }
}
