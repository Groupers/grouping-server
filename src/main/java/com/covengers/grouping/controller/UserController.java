package com.covengers.grouping.controller;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.dto.BringCrewListDto;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController extends AppApiV1Controller {
    private final UserService userService;
    private final CommonResponseMaker commonResponseMaker;

    @GetMapping("/users/groups/user-id")
    public CommonResponse<BringCrewListDto> bringCrewList(@RequestParam("user-id") String userId) {

        final BringCrewListDto responseDto =
                BringCrewListDto.of(userService.bringCrewList(userId));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }
}
