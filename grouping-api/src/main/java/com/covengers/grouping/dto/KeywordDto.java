package com.covengers.grouping.dto;

import com.covengers.grouping.vo.GroupVo;
import com.covengers.grouping.vo.KeywordVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class KeywordDto {
    private final String keyword;

    public static KeywordDto of(KeywordVo vo) {
        return builder()
                .keyword(vo.getKeyword())
                .build();
    }
}
