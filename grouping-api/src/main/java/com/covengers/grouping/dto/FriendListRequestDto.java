package com.covengers.grouping.dto;

import com.covengers.grouping.vo.FriendListRequestVo;
import com.covengers.grouping.vo.GroupingUserVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
public class FriendListRequestDto {
    private final List<GroupingUserVo> friendList;

    public static FriendListRequestDto of(FriendListRequestVo vo) {
        return builder()
                .friendList(vo.getFriendList())
                .build();
    }
}
