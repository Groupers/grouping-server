package com.covengers.grouping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.dto.CheckEmailResponseDto;
import com.covengers.grouping.dto.CheckPhoneNumberResponseDto;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.dto.GroupingUserDto;
import com.covengers.grouping.dto.SignInWithEmailRequestDto;
import com.covengers.grouping.dto.SignInWithPhoneNumberRequestDto;
import com.covengers.grouping.dto.SignUpRequestDto;
import com.covengers.grouping.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SignController extends AppApiV1Controller {

    private final UserService userService;
    private final CommonResponseMaker commonResponseMaker;

    @GetMapping("/sign/email")
    public CommonResponse<CheckEmailResponseDto> checkEmail(@RequestParam("email") String email) {

        final CheckEmailResponseDto responseDto =
                CheckEmailResponseDto.of(userService.checkEmail(email.toLowerCase()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/sign/phone-number")
    public CommonResponse<CheckPhoneNumberResponseDto> checkPhoneNumber(
            @RequestParam("phone-number") String phoneNumber) {

        final CheckPhoneNumberResponseDto responseDto =
                CheckPhoneNumberResponseDto.of(userService.checkPhoneNumber(phoneNumber.toLowerCase()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }
}