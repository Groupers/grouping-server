package com.covengers.grouping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.component.RequestContextHelper;
import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.dto.FriendListResultDto;
import com.covengers.grouping.dto.GroupListResponseDto;
import com.covengers.grouping.dto.GroupingUserDto;
import com.covengers.grouping.dto.ResetPasswordRequestDto;
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
    public CommonResponse<FriendListResultDto> getFriendList() {

        final GroupingUserInfoVo groupingUserInfoVo = requestContextHelper.getGroupingUserInfoVo();

        final FriendListResultDto responseDto =
                FriendListResultDto.of(userService.getFriendList(groupingUserInfoVo.getGroupingUserId()));

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
