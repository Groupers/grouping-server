package com.covengers.grouping.dto;

import com.covengers.grouping.domain.Keyword;
import com.covengers.grouping.vo.FriendListResultVo;
import com.covengers.grouping.vo.SearchListResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
public class SearchListResponseDto {
    private final List<Keyword> searchList;

    public static SearchListResponseDto of(SearchListResponseVo vo) {
        return builder()
                .searchList(vo.getSearchList())
                .build();
    }
}
