package com.covengers.grouping.controller;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.dto.ChatRoomResponseDto;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.service.AuthService;
import com.covengers.grouping.vo.ChatRoomResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final AuthService authService;
    private final CommonResponseMaker commonResponseMaker;

    @PostMapping("/room")
    public CommonResponse<ChatRoomResponseDto> createChatRoom(@RequestParam String title) {

        final ChatRoomResponseVo chatRoomResponseVo = authService.createChatRoom(title);

        return commonResponseMaker.makeSucceedCommonResponse(ChatRoomResponseDto.of(chatRoomResponseVo));
    }

    @PostMapping("/room/enter")
    public CommonResponse<ChatRoomResponseDto> enterChatRoom(@RequestParam Long chatRoomId) {

        final ChatRoomResponseVo chatRoomResponseVo = authService.enterChatRoom(chatRoomId);

        return commonResponseMaker.makeSucceedCommonResponse(ChatRoomResponseDto.of(chatRoomResponseVo));
    }

}
