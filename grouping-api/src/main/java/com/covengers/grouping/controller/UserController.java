package com.covengers.grouping.controller;

import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.dto.*;
import com.covengers.grouping.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController extends AppApiV1Controller {
    private final UserService userService;
    private final CommonResponseMaker commonResponseMaker;

    @GetMapping("/users/{groupingUserId}/groups")
    public CommonResponse<GroupListResponseDto> getGroupList(@PathVariable("groupingUserId") Long groupingUserId) {

        final GroupListResponseDto responseDto =
                GroupListResponseDto.of(userService.getGroupList(groupingUserId));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/users/{groupingUserId}/friends")
    public CommonResponse<FriendListResultDto> getFriendList(@PathVariable("groupingUserId") Long groupingUserId) {

        final FriendListResultDto responseDto =
                FriendListResultDto.of(userService.getFriendList(groupingUserId));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/users")
    public CommonResponse<GroupingUserDto> checkUserWithEmailAndPhoneNumber(
            @RequestParam String email, @RequestParam String phoneNumber) {

        final GroupingUserDto groupingUserDto =
                GroupingUserDto.of(userService.checkUserWithEmailAndPhoneNumber(email, phoneNumber));

        return commonResponseMaker.makeSucceedCommonResponse(groupingUserDto);
    }

    @PutMapping("/users/{groupingUserId}/password")
    public CommonResponse<Void> resetPassword(
            @PathVariable Long groupingUserId, @RequestBody ResetPasswordRequestDto requestDto) {

        userService.resetPassword(groupingUserId, requestDto.toVo());

        return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
    }
}
