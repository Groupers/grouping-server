package com.covengers.grouping.controller;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.dto.EnrollGroupRequestDto;
import com.covengers.grouping.dto.GroupDto;
import com.covengers.grouping.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GroupController {

    private final GroupService groupService;
    private final CommonResponseMaker commonResponseMaker;

    @PostMapping("/group/complete")
    public CommonResponse<GroupDto> enrollGroup(@RequestBody EnrollGroupRequestDto requestDto){

        final GroupDto responseDto = GroupDto.of(groupService.enrollGroup(requestDto.toVo()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

}
