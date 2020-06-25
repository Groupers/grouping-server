package com.covengers.grouping.vo;

import com.covengers.grouping.constant.MessageType;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class MessageVo {
    private final Long id;

    private final String contents;

    private final MessageType type;

    private final GroupingUserVo groupingUserVo;

    private final ChatRoomVo chatRoomVo;
}
