package com.covengers.grouping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.dto.CancelEmailRequestDto;
import com.covengers.grouping.dto.CancelPhoneNumberRequestDto;
import com.covengers.grouping.dto.CancelSignUpRequestDto;
import com.covengers.grouping.dto.CheckEmailResponseDto;
import com.covengers.grouping.dto.CheckPhoneNumberResponseDto;
import com.covengers.grouping.dto.CheckUserIdResponseDto;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.dto.EnrollEmailRequestDto;
import com.covengers.grouping.dto.EnrollPhoneNumberRequestDto;
import com.covengers.grouping.dto.GroupingUserDto;
import com.covengers.grouping.dto.SignInRequestDto;
import com.covengers.grouping.dto.SignUpRequestDto;
import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.service.UserService;

import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/sign/user-id")
    public CommonResponse<CheckUserIdResponseDto> checkUserId(@RequestParam("user-id") String userId) {

        final CheckUserIdResponseDto responseDto =
                CheckUserIdResponseDto.of(userService.checkUserId(userId.toLowerCase()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @ApiOperation(
            value = "enroll email",
            notes = "[ 응답코드별 상태 ]" +
                    "\n1000 : 이미 존재하는 이메일입니다.." +
                    "\n9999 : 서비스 점검 중"
    )
    @PostMapping("/sign/email")
    public CommonResponse<Void> enrollEmail(@RequestBody EnrollEmailRequestDto requestDto) {

        userService.enrollEmail(requestDto.toVo());

        return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
    }

    @PostMapping("/sign/phone-number")
    public CommonResponse<Void> enrollPhoneNumber(@RequestBody EnrollPhoneNumberRequestDto requestDto) {

        userService.enrollPhoneNumber(requestDto.toVo());

        return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
    }

    @PostMapping("/sign/cancel/email")
    public CommonResponse<Void> cancelSignUpEmail(@RequestBody CancelEmailRequestDto requestDto) {

        userService.cancelSignUpEmail(requestDto.toVo());
        return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
    }

    @PostMapping("/sign/cancel/phone-number")
    public CommonResponse<Void> cancelSignUpPhoneNumber(@RequestBody CancelPhoneNumberRequestDto requestDto) {

        userService.cancelSignUpPhoneNumber(requestDto.toVo());
        return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
    }

    @PostMapping("/sign/cancel")
    public CommonResponse<Void> cancelSignUp(@RequestBody CancelSignUpRequestDto requestDto) {

        userService.cancelSignUp(requestDto.toVo());

        return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
    }

    @PostMapping("/sign/complete")
    public CommonResponse<GroupingUserDto> completeSignUp(@RequestBody SignUpRequestDto requestDto) {

        final GroupingUserDto responseDto = GroupingUserDto.of(userService.completeSignUp(requestDto.toVo()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @PostMapping("/sign/login")
    public CommonResponse<GroupingUserDto> signIn(@RequestBody SignInRequestDto requestDto) {

        final GroupingUserDto responseDto = GroupingUserDto.of(userService.signIn(requestDto.toVo()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

}
