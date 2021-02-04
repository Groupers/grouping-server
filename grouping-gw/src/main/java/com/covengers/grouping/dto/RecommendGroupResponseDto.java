package com.covengers.grouping.dto;

import com.covengers.grouping.vo.RecommendGroupVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@Builder
public class RecommendGroupResponseDto {
    private final List<GroupResponseDto> groupList;

    public static RecommendGroupResponseDto of(RecommendGroupVo vo) {
        return builder()
                .groupList(
                        vo.getGroupList()
                                .stream()
                                .map(groupVo -> GroupResponseDto.of(groupVo))
                                .collect(Collectors.toList())
                )
                .build();
    }

    public RecommendGroupVo toVo() {
        return RecommendGroupVo.builder()
                .groupList(
                        groupList
                                .stream()
                                .map(groupDto -> groupDto.toVo())
                                .collect(Collectors.toList()))
                .build();
    }

}
