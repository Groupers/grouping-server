package com.covengers.grouping.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class GroupListResponseVo {
    private final List<GroupVo> groupList;
}
