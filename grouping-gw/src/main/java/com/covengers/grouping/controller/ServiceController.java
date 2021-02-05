package com.covengers.grouping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.dto.CreateGroupRequestDto;
import com.covengers.grouping.dto.FriendListResponseDto;
import com.covengers.grouping.dto.GroupListResponseDto;
import com.covengers.grouping.dto.GroupResponseDto;
import com.covengers.grouping.dto.GroupingUserResponseDto;
import com.covengers.grouping.dto.JwtTokenDto;
import com.covengers.grouping.dto.RecommendGroupResponseDto;
import com.covengers.grouping.dto.ResetPasswordRequestDto;
import com.covengers.grouping.dto.SearchHistoryListResponseDto;
import com.covengers.grouping.dto.SearchTrendsListResponseDto;
import com.covengers.grouping.dto.SignInWithEmailRequestDto;
import com.covengers.grouping.dto.SignInWithPhoneNumberRequestDto;
import com.covengers.grouping.dto.SignUpCheckEmailResponseDto;
import com.covengers.grouping.dto.SignUpCheckPhoneNumberResponseDto;
import com.covengers.grouping.dto.SignUpRequestDto;
import com.covengers.grouping.service.AuthService;
import com.covengers.grouping.vo.FriendListResponseVo;
import com.covengers.grouping.vo.GroupListResponseVo;
import com.covengers.grouping.vo.GroupResponseVo;
import com.covengers.grouping.vo.GroupingUserResponseVo;
import com.covengers.grouping.vo.JwtTokenVo;
import com.covengers.grouping.vo.RecommendGroupResponseVo;
import com.covengers.grouping.vo.SearchHistoryListResponseVo;
import com.covengers.grouping.vo.SearchTrendsListResponseVo;

import lombok.RequiredArgsConstructor;

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
    public CommonResponse<RecommendGroupResponseDto> recommendGroup(@RequestParam String keyword) {

        final RecommendGroupResponseVo recommendGroupResponseVo = authService.recommendGroup(keyword);

        return commonResponseMaker.makeSucceedCommonResponse(
                RecommendGroupResponseDto.of(recommendGroupResponseVo));
    }

    @GetMapping("/keywords/search/history")
    public CommonResponse<SearchHistoryListResponseDto> getSearchHistoryList() {

        final SearchHistoryListResponseVo searchHistoryListResponseVo =
                authService.getSearchHistoryList();

        return commonResponseMaker.makeSucceedCommonResponse(
                SearchHistoryListResponseDto.of(searchHistoryListResponseVo));
    }

    @GetMapping("/keywords/search/trends")
    public CommonResponse<SearchTrendsListResponseDto> getSearchTrendsList() {

        final SearchTrendsListResponseVo searchTrendsListResponseVo =
                authService.getSearchTrendsList();

        return commonResponseMaker.makeSucceedCommonResponse(
                SearchTrendsListResponseDto.of(searchTrendsListResponseVo));
    }

    @GetMapping("/users/groups")
    public CommonResponse<GroupListResponseDto> getGroupList() {

        final GroupListResponseVo groupListResponseVo =
                authService.getGroupList();

        return commonResponseMaker.makeSucceedCommonResponse(GroupListResponseDto.of(groupListResponseVo));
    }

    @GetMapping("/users/friends")
    public CommonResponse<FriendListResponseDto> getFriendList() {

        final FriendListResponseVo friendListResponseVo =
                authService.getFriendList();

        return commonResponseMaker.makeSucceedCommonResponse(FriendListResponseDto.of(friendListResponseVo));
    }

    @GetMapping("/users")
    public CommonResponse<GroupingUserResponseDto> checkUserWithEmailAndPhoneNumber(
            @RequestParam String email, @RequestParam String phoneNumber) {

        final GroupingUserResponseVo groupingUserResponseVo =
                authService.checkUserWithEmailAndPhoneNumber(email, phoneNumber);

        return commonResponseMaker.makeSucceedCommonResponse(
                GroupingUserResponseDto.of(groupingUserResponseVo));
    }

    @PutMapping("/users/password")
    public CommonResponse<Void> resetPassword(
            @RequestParam Long groupingUserId, @RequestBody ResetPasswordRequestDto requestDto) {

        authService.resetPassword(groupingUserId, requestDto.toVo());

        return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
    }
}
