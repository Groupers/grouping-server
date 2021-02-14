package com.covengers.grouping.service;

import com.covengers.grouping.domain.ChatMessage;
import com.covengers.grouping.domain.ChatRoom;
import com.covengers.grouping.repository.ChatRoomRepository;
import com.covengers.grouping.vo.ChatRoomVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final SimpMessageSendingOperations messagingTemplate;

    public ChatRoomVo createChatRoom(String title) {
        final ChatRoom chatRoom = new ChatRoom(title);
        chatRoomRepository.save(chatRoom);
        return chatRoom.toVo();
    }

    /**
     * Not Used Now
     * public ChatRoomVo enterChatRoom(Long chatRoomId) {
     * final ChatRoom chatRoom = chatRoomRepository.getOne(chatRoomId);
     * final String topicId = chatRoom.getTopicId();
     * return chatRoom.toVo();
     * }
     */

    public void sendMessage(ChatMessage chatMessage) {
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getTopicId(), chatMessage);
    }

}
