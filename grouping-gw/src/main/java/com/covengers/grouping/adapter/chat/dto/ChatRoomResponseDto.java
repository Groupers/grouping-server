package com.covengers.grouping.adapter.chat.dto;

import com.covengers.grouping.vo.ChatRoomResponseVo;
import com.covengers.grouping.vo.ChatRoomVo;
import com.covengers.grouping.vo.GroupResponseVo;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomResponseDto {

    private Long id;

    private String topicId;

    private String title;

    public ChatRoomResponseVo toVo() {
        return ChatRoomResponseVo.builder()
                .id(id)
                .topicId((topicId))
                .title(title)
                .build();
    }
}
