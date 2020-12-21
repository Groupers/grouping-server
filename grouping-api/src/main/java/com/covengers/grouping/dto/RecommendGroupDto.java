package com.covengers.grouping.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.covengers.grouping.vo.GroupVo;
import com.covengers.grouping.vo.RecommendGroupVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class RecommendGroupDto {
    private final List<GroupDto> groupList;

    public static RecommendGroupDto of(RecommendGroupVo vo) {
        return builder()
                .groupList(
                        vo.getGroupList()
                                .stream()
                                .map(groupVo -> GroupDto.of(groupVo))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
