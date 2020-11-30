package com.covengers.grouping.domain;

import com.covengers.grouping.vo.ChatRoomVo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = -3528943370988201172L;

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
