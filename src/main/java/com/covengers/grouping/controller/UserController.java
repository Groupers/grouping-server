package com.covengers.grouping.controller;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.dto.CrewListResponseDto;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController extends AppApiV1Controller {
    private final UserService userService;
    private final CommonResponseMaker commonResponseMaker;

    @GetMapping("/users/groups/{userId}")
    public CommonResponse<CrewListResponseDto> getCrewList(@PathVariable("userId") String userId) {

        final CrewListResponseDto responseDto =
                CrewListResponseDto.of(userService.getCrewList(userId));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }
}
