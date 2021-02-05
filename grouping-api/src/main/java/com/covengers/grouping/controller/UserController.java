package com.covengers.grouping.controller;

import com.covengers.grouping.component.RequestContextHelper;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.dto.*;
import com.covengers.grouping.service.UserService;
import com.covengers.grouping.vo.GroupingUserInfoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users")
    public CommonResponse<GroupingUserDto> checkUserWithEmailAndPhoneNumber(
            @RequestParam String email, @RequestParam String phoneNumber) {

        final GroupingUserDto groupingUserDto =
                GroupingUserDto.of(userService.checkUserWithEmailAndPhoneNumber(email, phoneNumber));

        return commonResponseMaker.makeSucceedCommonResponse(groupingUserDto);
    }

    @PutMapping("/users/password")
    public CommonResponse<Void> resetPassword(
            @RequestParam Long groupingUserId, @RequestBody ResetPasswordRequestDto requestDto) {

        userService.resetPassword(groupingUserId, requestDto.toVo());

        return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
    }
}
