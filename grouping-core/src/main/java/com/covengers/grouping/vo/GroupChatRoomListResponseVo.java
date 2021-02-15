package com.covengers.grouping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
public class GroupChatRoomListResponseVo {
    private final List<GroupChatRoomVo> groupChatRoomList;
}
