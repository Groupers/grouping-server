package com.covengers.grouping.dto;

import com.covengers.grouping.vo.ChatRoomVo;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomDto {

    private Long id;

    private String topicId;

    private String title;

    public static ChatRoomDto of(ChatRoomVo vo) {
        return builder()
                .id(vo.getId())
                .topicId(vo.getTopicId())
                .title(vo.getTitle())
                .build();
    }
}
