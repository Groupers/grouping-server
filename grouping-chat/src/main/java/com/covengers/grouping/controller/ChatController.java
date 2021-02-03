package com.covengers.grouping.controller;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.domain.ChatMessage;
import com.covengers.grouping.dto.ChatRoomDto;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController extends AppChatV1Controller {

    private final ChatService chatService;
    private final CommonResponseMaker commonResponseMaker;

    @PostMapping("/room")
    public CommonResponse<ChatRoomDto> createChatRoom(@RequestParam String title) {
        final ChatRoomDto responseDto = ChatRoomDto.of(chatService.createChatRoom(title));
        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @PostMapping("/room/enter")
    public CommonResponse<ChatRoomDto> enterChatRoom(@RequestParam Long id) {
        final ChatRoomDto responseDto = ChatRoomDto.of(chatService.enterChatRoom(id));
        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessage chatMessage) {
        chatService.sendMessage(chatMessage);
    }
    
}
