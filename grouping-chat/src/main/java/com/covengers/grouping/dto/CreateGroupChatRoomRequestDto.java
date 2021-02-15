package com.covengers.grouping.dto;

import com.covengers.grouping.vo.CreateGroupChatRoomRequestVo;
import com.covengers.grouping.vo.CreateGroupRequestVo;
import lombok.*;

import java.util.List;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateGroupChatRoomRequestDto {
    private String chatRoomTitle;

    private Long groupId;

    private List<Long> groupingUserIdList;

    public CreateGroupChatRoomRequestVo toVo() {
        return CreateGroupChatRoomRequestVo.builder()
                .chatRoomTitle(chatRoomTitle)
                .groupId(groupId)
                .groupingUserIdList(groupingUserIdList)
                .build();
    }
}
