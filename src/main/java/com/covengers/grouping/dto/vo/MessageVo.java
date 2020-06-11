package com.covengers.grouping.dto.vo;

import com.covengers.grouping.constant.Gender;

import com.covengers.grouping.constant.MessageType;
import com.covengers.grouping.domain.ChatRoom;
import com.covengers.grouping.domain.GroupingUser;
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

    private final GroupingUser groupingUser;

    private final ChatRoom chatRoom;
}
