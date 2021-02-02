package com.covengers.grouping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.dto.GroupingUserDto;
import com.covengers.grouping.dto.SignUpRequestDto;
import com.covengers.grouping.service.AuthService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ServiceController extends AppGwV1Controller {

    private final AuthService authService;
    private final CommonResponseMaker commonResponseMaker;

    @PostMapping("/sign/complete")
    public CommonResponse<GroupingUserDto> completeSignUp(@RequestBody SignUpRequestDto requestDto) {

        final GroupingUserDto responseDto = GroupingUserDto.of(authService.completeSignUp(requestDto.toVo()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

}
