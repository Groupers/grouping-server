package com.covengers.grouping.adapter.api.dto;

import com.covengers.grouping.vo.FriendListResponseVo;
import com.covengers.grouping.vo.GroupListResponseVo;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendListResponseDto {
    private List<GroupingUserResponseDto> friendList;

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

    public FriendListResponseVo toVo() {
        return FriendListResponseVo.builder()
                .friendList(
                        friendList
                                .stream()
                                .map(groupingUserResponseDto -> groupingUserResponseDto.toVo())
                                .collect(Collectors.toList()))
                .build();
    }
}
