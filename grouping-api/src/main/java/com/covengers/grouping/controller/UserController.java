package com.covengers.grouping.controller;

import com.covengers.grouping.dto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.component.RequestContextHelper;
import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.service.UserService;
import com.covengers.grouping.vo.GroupingUserInfoVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController extends AppApiV1Controller {
    private final UserService userService;
    private final CommonResponseMaker commonResponseMaker;
    private final RequestContextHelper requestContextHelper;

    @GetMapping("/users/groups")
    public CommonResponse<GroupListResponseDto> getGroupList() {

        final GroupingUserInfoVo groupingUserInfoVo = requestContextHelper.getGroupingUserInfoVo();

        final GroupListResponseDto responseDto =
                GroupListResponseDto.of(userService.getGroupList(groupingUserInfoVo.getGroupingUserId()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/users/friends")
    public CommonResponse<FriendListResponseDto> getFriendList() {

        final GroupingUserInfoVo groupingUserInfoVo = requestContextHelper.getGroupingUserInfoVo();

        final FriendListResponseDto responseDto =
                FriendListResponseDto.of(userService.getFriendList(groupingUserInfoVo.getGroupingUserId()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/users/info")
    public CommonResponse<GroupingUserDto> getUserInfo() {

        final GroupingUserInfoVo groupingUserInfoVo = requestContextHelper.getGroupingUserInfoVo();

        final GroupingUserDto groupingUserDto =
                GroupingUserDto.of(userService.getUserInfo(groupingUserInfoVo));

        return commonResponseMaker.makeSucceedCommonResponse(groupingUserDto);
    }

    @PutMapping("/users/password")
    public CommonResponse<Void> resetPassword(@RequestBody ResetPasswordRequestDto requestDto) {

        final GroupingUserInfoVo groupingUserInfoVo = requestContextHelper.getGroupingUserInfoVo();

        userService.resetPassword(groupingUserInfoVo.getGroupingUserId(), requestDto.toVo());

        return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
    }
}
