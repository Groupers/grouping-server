package com.covengers.grouping.controller;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.domain.ChatMessage;
import com.covengers.grouping.dto.CreateGroupChatRoomRequestDto;
import com.covengers.grouping.dto.GroupChatRoomDto;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController extends AppChatV1Controller {

    private final ChatService chatService;
    private final CommonResponseMaker commonResponseMaker;

    @PostMapping("/chat/room-group")
    public CommonResponse<GroupChatRoomDto> createGroupChatRoom(
            @RequestBody CreateGroupChatRoomRequestDto requestDto) {

        final GroupChatRoomDto responseDto = GroupChatRoomDto.of(
                chatService.createGroupChatRoom(requestDto.toVo()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    /**
     * Not Used Now
    @PostMapping("/chat/room/enter")
    public CommonResponse<ChatRoomDto> enterChatRoom(@RequestParam Long chatRoomId) {
        final ChatRoomDto responseDto = ChatRoomDto.of(chatService.enterChatRoom(chatRoomId));
        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }
    */

    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessage chatMessage) {
        chatService.sendMessage(chatMessage);
    }
    
}
