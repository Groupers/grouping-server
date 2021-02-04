package com.covengers.grouping.dto;

import com.covengers.grouping.vo.GroupListResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@ToString
public class GroupListResponseDto {
    private final List<GroupResponseDto> groupList;

    public static GroupListResponseDto of(GroupListResponseVo vo){
        return builder()
                .groupList(
                        vo.getGroupList()
                                .stream()
                                .map(groupResponseVo -> GroupResponseDto.of(groupResponseVo))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
