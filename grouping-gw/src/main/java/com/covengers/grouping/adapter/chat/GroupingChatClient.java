package com.covengers.grouping.adapter.chat;

import com.covengers.grouping.adapter.chat.domain.ChatMessage;
import com.covengers.grouping.adapter.chat.dto.ChatRoomResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.covengers.grouping.configuration.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "grouping-chat",
        url = "${grouping.url.chat}",
        configuration = {
                FeignConfiguration.class,
        })
@RequestMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public interface GroupingChatClient {

        @PostMapping("/room")
        GroupingChatAdapterResponse<ChatRoomResponseDto> createChatRoom(@RequestParam String title);

        @PostMapping("/room/enter")
        GroupingChatAdapterResponse<ChatRoomResponseDto> enterChatRoom(@RequestParam Long chatRoomId);

        @MessageMapping("/chat/message")
        GroupingChatAdapterResponse<Void> sendMessage(ChatMessage chatMessage);

}