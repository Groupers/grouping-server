package com.covengers.grouping.dto;

import com.covengers.grouping.vo.FriendListResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@Builder
public class FriendListResponseDto {
    private final List<GroupingUserResponseDto> friendList;

    public static FriendListResponseDto of(FriendListResponseVo vo) {
        return builder()
                .friendList(
                        vo.getFriendList()
                                .stream()
                                .map(groupingUserResponseVo -> GroupingUserResponseDto.of(groupingUserResponseVo))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
