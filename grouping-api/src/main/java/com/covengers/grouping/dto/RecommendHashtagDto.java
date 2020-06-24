package com.covengers.grouping.dto;

import java.util.List;

import com.covengers.grouping.vo.RecommendHashtagVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class RecommendHashtagDto {
    private final List<String> hashtagList;

    public RecommendHashtagDto of(RecommendHashtagVo vo) {
        return builder()
                .hashtagList(vo.getHashtagList())
                .build();
    }
}
