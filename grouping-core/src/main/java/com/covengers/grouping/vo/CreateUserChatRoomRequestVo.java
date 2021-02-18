package com.covengers.grouping.vo;

import lombok.*;

import java.util.List;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserChatRoomRequestVo {

    private List<Long> groupingUserIdList;
}
