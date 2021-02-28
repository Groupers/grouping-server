package com.covengers.grouping.dto;

import com.covengers.grouping.vo.CreateUserChatRoomRequestVo;
import lombok.*;

import java.util.List;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserChatRoomRequestDto {

    private List<Long> groupingUserIdList;

    public CreateUserChatRoomRequestVo toVo() {
        return CreateUserChatRoomRequestVo.builder()
                .groupingUserIdList(groupingUserIdList)
                .build();
    }
}
