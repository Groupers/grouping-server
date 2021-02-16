package com.covengers.grouping.dto;

import com.covengers.grouping.vo.GroupChatRoomListResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@ToString
public class GroupChatRoomListResponseDto {
    private final List<GroupChatRoomDto> groupChatRoomList;

    public static GroupChatRoomListResponseDto of(GroupChatRoomListResponseVo vo) {
        return builder()
                .groupChatRoomList(
                        vo.getGroupChatRoomList()
                                .stream()
                                .map(groupChatRoomVo -> GroupChatRoomDto.of(groupChatRoomVo))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
