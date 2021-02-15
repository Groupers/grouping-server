package com.covengers.grouping.vo;

import lombok.*;

import java.util.List;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateGroupChatRoomRequestVo {
    private String chatRoomTitle;

    private Long groupId;

    private List<Long> groupingUserIdList;
}
