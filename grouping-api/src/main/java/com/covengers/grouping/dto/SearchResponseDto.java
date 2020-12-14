package com.covengers.grouping.dto;

import com.covengers.grouping.vo.KeywordVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SearchResponseDto {
    private final String keyword;

    public static SearchResponseDto of(KeywordVo vo) {
        return builder()
                .keyword(vo.getKeyword())
                .build();
    }
}
