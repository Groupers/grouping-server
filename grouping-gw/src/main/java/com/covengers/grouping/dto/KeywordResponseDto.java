package com.covengers.grouping.dto;

import com.covengers.grouping.vo.KeywordResponseVo;
import com.covengers.grouping.vo.KeywordVo;
import lombok.*;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class KeywordResponseDto {
    private String keyword;

    public static KeywordResponseDto of(KeywordResponseVo vo) {
        return builder()
                .keyword(vo.getKeyword())
                .build();
    }

    public KeywordResponseVo toVo() {
        return KeywordResponseVo.builder()
                .keyword(keyword)
                .build();
    }
}
