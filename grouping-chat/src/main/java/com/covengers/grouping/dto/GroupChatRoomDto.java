package com.covengers.grouping.dto;

import com.covengers.grouping.vo.GroupChatRoomVo;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupChatRoomDto {

    private Long id;

    private String topicId;

    private String title;

    public static GroupChatRoomDto of(GroupChatRoomVo vo) {
        return builder()
                .id(vo.getId())
                .topicId(vo.getTopicId())
                .title(vo.getTitle())
                .build();
    }
}
