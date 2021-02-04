package com.covengers.grouping.controller;

import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.dto.*;
import com.covengers.grouping.vo.*;
import org.springframework.web.bind.annotation.*;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ServiceController extends AppGwV1Controller {

    private final AuthService authService;
    private final CommonResponseMaker commonResponseMaker;

    @GetMapping("/sign/email")
    public CommonResponse<SignUpCheckEmailResponseDto> checkSignUpEmail(@RequestParam("email") String email) {

        final SignUpCheckEmailResponseDto responseDto =
                SignUpCheckEmailResponseDto.of(authService.checkSignUpEmail(email));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/sign/phone-number")
    public CommonResponse<SignUpCheckPhoneNumberResponseDto> checkPhoneNumber(
            @RequestParam("phone-number") String phoneNumber) {

        final SignUpCheckPhoneNumberResponseDto responseDto =
                SignUpCheckPhoneNumberResponseDto.of(authService.checkSignUpPhoneNumber(phoneNumber));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @PostMapping("/sign/complete")
    public CommonResponse<JwtTokenDto> completeSignUp(@RequestBody SignUpRequestDto requestDto) {

        final JwtTokenVo jwtTokenVo = authService.completeSignUp(requestDto.toVo());

        return commonResponseMaker.makeSucceedCommonResponse(JwtTokenDto.of(jwtTokenVo));
    }

    @PostMapping("/sign/login/email")
    public CommonResponse<JwtTokenDto> signInWithEmail(@RequestBody SignInWithEmailRequestDto requestDto) {

        final JwtTokenVo jwtTokenVo = authService.signInWithEmail(requestDto.toVo());

        return commonResponseMaker.makeSucceedCommonResponse(JwtTokenDto.of(jwtTokenVo));
    }

    @PostMapping("/sign/login/phone-number")
    public CommonResponse<JwtTokenDto> signInWithPhoneNumber(
            @RequestBody SignInWithPhoneNumberRequestDto requestDto) {

        final JwtTokenVo jwtTokenVo = authService.signInWithPhoneNumber(requestDto.toVo());

        return commonResponseMaker.makeSucceedCommonResponse(JwtTokenDto.of(jwtTokenVo));
    }

    @PostMapping("/group")
    public CommonResponse<GroupResponseDto> createGroup(@RequestBody CreateGroupRequestDto requestDto) {

        final GroupResponseVo groupResponseVo = authService.createGroup(requestDto.toVo());

        return commonResponseMaker.makeSucceedCommonResponse(GroupResponseDto.of(groupResponseVo));

    }

    @PostMapping("/group/image")
    public CommonResponse<GroupResponseDto> uploadGroupImage(
            @RequestPart("imageFile") MultipartFile imageFile,
            @RequestParam final Long groupId
    ) {

        final GroupResponseVo groupResponseVo = authService.uploadGroupImage(imageFile, groupId);

        return commonResponseMaker.makeSucceedCommonResponse(GroupResponseDto.of(groupResponseVo));
    }

    @GetMapping("/group/keyword")
    public CommonResponse<RecommendGroupResponseDto> recommendGroup(@RequestParam Long groupingUserId, @RequestParam String keyword) {

        final RecommendGroupResponseVo recommendGroupResponseVo = authService.recommendGroup(groupingUserId, keyword);

        return commonResponseMaker.makeSucceedCommonResponse(RecommendGroupResponseDto.of(recommendGroupResponseVo));
    }

    @GetMapping("/keywords/search/history")
    public CommonResponse<SearchHistoryListResponseDto> getSearchHistoryList(@RequestParam Long groupingUserId) {

        final SearchHistoryListResponseVo searchHistoryListResponseVo =
                authService.getSearchHistoryList(groupingUserId);

        return commonResponseMaker.makeSucceedCommonResponse(SearchHistoryListResponseDto.of(searchHistoryListResponseVo));
    }

    @GetMapping("/keywords/search/trends")
    public CommonResponse<SearchTrendsListResponseDto> getSearchTrendsList() {

        final SearchTrendsListResponseVo searchTrendsListResponseVo =
                authService.getSearchTrendsList();

        return commonResponseMaker.makeSucceedCommonResponse(SearchTrendsListResponseDto.of(searchTrendsListResponseVo));
    }

    @GetMapping("/users/groups")
    public CommonResponse<GroupListResponseDto> getGroupList(@RequestParam Long groupingUserId) {

        final GroupListResponseVo groupListResponseVo =
                authService.getGroupList(groupingUserId);

        return commonResponseMaker.makeSucceedCommonResponse(GroupListResponseDto.of(groupListResponseVo));
    }

    @GetMapping("/users/friends")
    public CommonResponse<FriendListResponseDto> getFriendList(@RequestParam Long groupingUserId) {

        final FriendListResponseVo friendListResponseVo =
                authService.getFriendList(groupingUserId);

        return commonResponseMaker.makeSucceedCommonResponse(FriendListResponseDto.of(friendListResponseVo));
    }

    @GetMapping("/users")
    public CommonResponse<GroupingUserResponseDto> checkUserWithEmailAndPhoneNumber(
            @RequestParam String email, @RequestParam String phoneNumber) {

        final GroupingUserResponseVo groupingUserResponseVo =
                authService.checkUserWithEmailAndPhoneNumber(email, phoneNumber);

        return commonResponseMaker.makeSucceedCommonResponse(GroupingUserResponseDto.of(groupingUserResponseVo));
    }

    @PutMapping("/users/password")
    public CommonResponse<Void> resetPassword(
            @RequestParam Long groupingUserId, @RequestBody ResetPasswordRequestDto requestDto) {

        authService.resetPassword(groupingUserId, requestDto.toVo());

        return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
    }

}
