package com.covengers.grouping.dto;

import com.covengers.grouping.constant.MessageType;
import com.covengers.grouping.vo.ChatRoomVo;
import com.covengers.grouping.vo.GroupingUserVo;
import com.covengers.grouping.vo.MessageVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class MessageDto {
    private final long id;
    private final String contents;
    private final MessageType type;
    private final GroupingUserVo groupingUserVo;
    private final ChatRoomVo chatRoomVo;

    public MessageVo toVo() {
        return MessageVo.builder()
                .id(id)
                .contents(contents)
                .type(type)
                .groupingUserVo(groupingUserVo)
                .chatRoomVo(chatRoomVo)
                .build();
    }

    public static MessageDto of(MessageVo vo) {
        return builder()
                .id(vo.getId())
                .contents(vo.getContents())
                .type(vo.getType())
                .groupingUserVo(vo.getGroupingUserVo())
                .chatRoomVo(vo.getChatRoomVo())
                .build();
    }
}
