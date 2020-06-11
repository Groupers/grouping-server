package com.covengers.grouping.dto;

import com.covengers.grouping.dto.vo.ChatRoomVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ChatRoomDto {
    private final Long id;
    private final String name;
    private final String imagePath;

    public ChatRoomDto of(ChatRoomVo vo) {
        return builder()
                .id(vo.getId())
                .name(vo.getName())
                .imagePath(vo.getImagePath())
                .build();
    }

    public ChatRoomVo toVo() {
        return ChatRoomVo.builder()
                .id(id)
                .name(name)
                .imagePath(imagePath)
                .build();
    }
}
