package com.covengers.grouping.dto;

import com.covengers.grouping.vo.FriendListResultVo;
import com.covengers.grouping.vo.GroupingUserVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@Builder
public class FriendListResultDto {
    private final List<GroupingUserDto> friendList;

    public static FriendListResultDto of(FriendListResultVo vo) {
        return builder()
                .friendList(
                        vo.getFriendList()
                                .stream()
                                .map(groupingUserVo -> GroupingUserDto.of(groupingUserVo))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
