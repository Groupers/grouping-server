package com.covengers.grouping.domain;

import com.covengers.grouping.vo.ChatRoomVo;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public class ChatRoom {

    private String title;

    private String id;

    public static ChatRoom create(String title) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.id = UUID.randomUUID().toString();
        chatRoom.title = title;
        return chatRoom;
    }

    public ChatRoomVo toVo() {
        return ChatRoomVo.builder()
                .id(id)
                .title(title)
                .build();
    }
}
