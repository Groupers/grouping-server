package com.covengers.grouping.service;

import com.covengers.grouping.domain.ChatRoom;
import com.covengers.grouping.repository.ChatRoomRepository;
import com.covengers.grouping.vo.ChatRoomVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomVo createChatRoom(String title) {
        final ChatRoom chatRoom = chatRoomRepository.createChatRoom(title);
        return chatRoom.toVo();
    }

    public ChatRoomVo enterChatRoom(String chatRoomid) {
        final ChatRoom chatRoom = chatRoomRepository.enterChatRoom(chatRoomid);
        return chatRoom.toVo();
    }

}
