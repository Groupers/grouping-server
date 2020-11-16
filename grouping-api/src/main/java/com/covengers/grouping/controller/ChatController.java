package com.covengers.grouping.controller;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.domain.Message;
import com.covengers.grouping.dto.ChatRoomDto;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController {

    private final SimpMessageSendingOperations messageSendingOperations;

    private final ChatService chatService;

    private final CommonResponseMaker commonResponseMaker;

    @MessageMapping("/chat/message")
    public void sendMessage(Message message) {
        messageSendingOperations.convertAndSend("/sub/chat/room/" + message.getChatRoomId(), message);
    }

    @PostMapping("/chat/room")
    public CommonResponse<ChatRoomDto> createChatRoom(@RequestParam String title) {
        final ChatRoomDto responseDto = ChatRoomDto.of(chatService.createChatRoom(title));
        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }


}
