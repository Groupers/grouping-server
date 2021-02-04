package com.covengers.grouping.adapter.api.dto;

import com.covengers.grouping.vo.GroupListResponseVo;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GroupListResponseDto {
    private List<GroupResponseDto> groupList;

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

    public GroupListResponseVo toVo() {
        return GroupListResponseVo.builder()
                .groupList(
                        groupList
                                .stream()
                                .map(groupResponseDto -> groupResponseDto.toVo())
                                .collect(Collectors.toList()))
                .build();
    }
}
