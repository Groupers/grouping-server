package com.covengers.grouping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.dto.CreateGroupRequestDto;
import com.covengers.grouping.dto.GroupDto;
import com.covengers.grouping.dto.JwtTokenDto;
import com.covengers.grouping.dto.SignInWithEmailRequestDto;
import com.covengers.grouping.dto.SignInWithPhoneNumberRequestDto;
import com.covengers.grouping.dto.SignUpCheckEmailResponseDto;
import com.covengers.grouping.dto.SignUpCheckPhoneNumberResponseDto;
import com.covengers.grouping.dto.SignUpRequestDto;
import com.covengers.grouping.service.AuthService;
import com.covengers.grouping.vo.GroupVo;
import com.covengers.grouping.vo.JwtTokenVo;

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

    @PostMapping("/group")
    public CommonResponse<GroupDto> createGroup(@RequestBody CreateGroupRequestDto requestDto) {

        final GroupVo groupVo = authService.createGroup(requestDto.toVo());

        return commonResponseMaker.makeSucceedCommonResponse(GroupDto.of(groupVo));

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

}
