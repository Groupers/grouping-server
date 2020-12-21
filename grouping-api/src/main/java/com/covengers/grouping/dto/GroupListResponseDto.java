package com.covengers.grouping.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.covengers.grouping.vo.GroupListResponseVo;
import com.covengers.grouping.vo.GroupVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GroupListResponseDto {
    private final List<GroupDto> groupList;

    public static GroupListResponseDto of(GroupListResponseVo vo){
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
