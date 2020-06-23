package com.covengers.grouping.dto;

import com.covengers.grouping.dto.vo.RecommendHashtagVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

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
