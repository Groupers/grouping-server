package com.covengers.grouping.controller;

import com.covengers.grouping.dto.FriendListRequestDto;
import com.covengers.grouping.vo.FriendListRequestVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.dto.GroupListResponseDto;
import com.covengers.grouping.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController extends AppApiV1Controller {
    private final UserService userService;
    private final CommonResponseMaker commonResponseMaker;

    @GetMapping("/users/{userId}/groups")
    public CommonResponse<GroupListResponseDto> getCrewList(@PathVariable("userId") String userId) {

        final GroupListResponseDto responseDto =
                GroupListResponseDto.of(userService.getGroupList(userId));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/users/{userId}/friends")
    public CommonResponse<FriendListRequestDto> getFriendList(@PathVariable String userId) {

        final FriendListRequestDto responseDto =
                FriendListRequestDto.of(userService.getFriendList(userId));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }
}
