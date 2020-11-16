package com.covengers.grouping.dto;

import com.covengers.grouping.vo.ChatRoomVo;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomDto {

    private String id;

    private String title;

    public static ChatRoomDto of(ChatRoomVo vo) {
        return builder()
                .id(vo.getId())
                .title(vo.getTitle())
                .build();
    }
}
