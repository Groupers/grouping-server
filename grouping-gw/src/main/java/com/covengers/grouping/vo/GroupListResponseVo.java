package com.covengers.grouping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class GroupListResponseVo {
    private final List<GroupResponseVo> groupList;
}
