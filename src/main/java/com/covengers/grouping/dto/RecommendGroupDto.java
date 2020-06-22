package com.covengers.grouping.dto;

import com.covengers.grouping.dto.vo.RecommendGroupVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
public class RecommendGroupDto {
    private final List<String> groupList;

    public static RecommendGroupDto of(RecommendGroupVo vo) {
        return builder()
                .groupList(vo.getGroupList())
                .build();
    }
}
