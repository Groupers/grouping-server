package com.covengers.grouping.dto;

import com.covengers.grouping.dto.vo.GroupVo;
import com.covengers.grouping.dto.vo.RecommendGroupVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
public class RecommendGroupDto {
    private final List<GroupVo> groupList;

    public static RecommendGroupDto of(RecommendGroupVo vo) {
        return builder()
                .groupList(vo.getGroupList())
                .build();
    }
}
