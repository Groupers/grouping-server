package com.covengers.grouping.controller;

import com.covengers.grouping.dto.*;
import org.springframework.web.bind.annotation.*;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.service.AuthService;
import com.covengers.grouping.vo.GroupResponseVo;
import com.covengers.grouping.vo.JwtTokenVo;

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

        final GroupResponseDto responseDto = GroupResponseDto.of(authService.uploadGroupImage(imageFile, groupId));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/group/keyword")
    public CommonResponse<RecommendGroupResponseDto> recommendGroup(@RequestParam Long groupingUserId, @RequestParam String keyword) {

        final RecommendGroupResponseDto responseDto = RecommendGroupResponseDto.of(authService.recommendGroup(groupingUserId, keyword));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/keywords/search/history")
    public CommonResponse<SearchHistoryListResponseDto> getSearchHistoryList(@RequestParam Long groupingUserId) {

        final SearchHistoryListResponseDto responseDto =
                SearchHistoryListResponseDto.of(authService.getSearchHistoryList(groupingUserId));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/keywords/search/trends")
    public CommonResponse<SearchTrendsListResponseDto> getSearchTrendsList() {

        final SearchTrendsListResponseDto responseDto =
                SearchTrendsListResponseDto.of(authService.getSearchTrendsList());

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }
}
