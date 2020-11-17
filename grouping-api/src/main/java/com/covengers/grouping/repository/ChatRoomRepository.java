package com.covengers.grouping.repository;

import com.covengers.grouping.domain.ChatRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public ChatRoom create(String title) {
        ChatRoom chatRoom = ChatRoom.create(title);
        chatRoomMap.put(chatRoom.getId(), chatRoom);
        return chatRoom;
    }
}
