package com.covengers.grouping.dto;

import com.covengers.grouping.dto.vo.GroupListResponseVo;
import com.covengers.grouping.dto.vo.GroupVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class GroupListResponseDto {
    private final List<GroupVo> groupList;

    public static GroupListResponseDto of(GroupListResponseVo vo){
        return GroupListResponseDto.builder()
                .groupList(vo.getGroupList())
                .build();
    }
}
