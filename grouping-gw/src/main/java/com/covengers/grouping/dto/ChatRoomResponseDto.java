package com.covengers.grouping.dto;

import com.covengers.grouping.vo.ChatRoomResponseVo;
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

    public static ChatRoomResponseDto of(ChatRoomResponseVo vo) {
        return builder()
                .id(vo.getId())
                .topicId(vo.getTopicId())
                .title(vo.getTitle())
                .build();
    }
}
