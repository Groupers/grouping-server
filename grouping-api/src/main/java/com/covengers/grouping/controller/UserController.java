package com.covengers.grouping.controller;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.dto.FriendListResultDto;
import com.covengers.grouping.dto.GroupListResponseDto;
import com.covengers.grouping.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController extends AppApiV1Controller {
    private final UserService userService;
    private final CommonResponseMaker commonResponseMaker;

    @GetMapping("/users/{groupingUserId}/groups")
    public CommonResponse<GroupListResponseDto> getGroupList(@PathVariable("groupingUserId") String groupingUserId) {

        final GroupListResponseDto responseDto =
                GroupListResponseDto.of(userService.getGroupList(groupingUserId));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/users/{groupingUserId}/friends")
    public CommonResponse<FriendListResultDto> getFriendList(@PathVariable("groupingUserId") String groupingUserId) {

        final FriendListResultDto responseDto =
                FriendListResultDto.of(userService.getFriendList(groupingUserId));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }
}
