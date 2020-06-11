package com.covengers.grouping.dto;

import com.covengers.grouping.constant.MessageType;
import com.covengers.grouping.domain.ChatRoom;
import com.covengers.grouping.domain.GroupingUser;
import com.covengers.grouping.dto.vo.EnrollPhoneNumberRequestVo;
import com.covengers.grouping.dto.vo.GroupingUserVo;
import com.covengers.grouping.dto.vo.MessageVo;
import com.covengers.grouping.dto.vo.SignInRequestVo;

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
    private final GroupingUser groupingUser;
    private final ChatRoom chatRoom;

    public MessageVo toVo() {
        return MessageVo.builder()
                .id(id)
                .contents(contents)
                .type(type)
                .groupingUser(groupingUser)
                .chatRoom(chatRoom)
                .build();
    }

    public static MessageDto of(MessageVo vo) {
        return builder()
                .id(vo.getId())
                .contents(vo.getContents())
                .type(vo.getType())
                .groupingUser(vo.getGroupingUser())
                .chatRoom(vo.getChatRoom())
                .build();
    }
}
