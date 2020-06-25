package com.covengers.grouping.dto;

import com.covengers.grouping.vo.FriendListResultVo;
import com.covengers.grouping.vo.GroupingUserVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
public class FriendListResultDto {
    private final List<GroupingUserVo> friendList;

    public static FriendListResultDto of(FriendListResultVo vo) {
        return builder()
                .friendList(vo.getFriendList())
                .build();
    }
}
