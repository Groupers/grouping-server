package com.covengers.grouping.dto;

import com.covengers.grouping.vo.UserChatRoomVo;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserChatRoomDto {

    private Long id;

    private String topicId;

    public static UserChatRoomDto of(UserChatRoomVo vo) {
        return builder()
                .id(vo.getId())
                .topicId(vo.getTopicId())
                .build();
    }
}
