package com.covengers.grouping.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.dto.JwtTokenDto;
import com.covengers.grouping.dto.SignUpRequestDto;
import com.covengers.grouping.service.AuthService;
import com.covengers.grouping.vo.JwtTokenVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ServiceController extends AppGwV1Controller {

    private final AuthService authService;
    private final CommonResponseMaker commonResponseMaker;

    @PostMapping("/sign/complete")
    public CommonResponse<JwtTokenDto> completeSignUp(@RequestBody SignUpRequestDto requestDto) {

        final JwtTokenVo jwtTokenVo = authService.completeSignUp(requestDto.toVo());

        return commonResponseMaker.makeSucceedCommonResponse(JwtTokenDto.of(jwtTokenVo));
    }

}
