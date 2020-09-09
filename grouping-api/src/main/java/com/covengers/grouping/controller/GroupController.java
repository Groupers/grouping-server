package com.covengers.grouping.controller;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.dto.CreateGroupRequestDto;
import com.covengers.grouping.dto.GroupDto;
import com.covengers.grouping.dto.RecommendGroupDto;
import com.covengers.grouping.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GroupController extends AppApiV1Controller {

    private final GroupService groupService;
    private final CommonResponseMaker commonResponseMaker;

    @PostMapping("/group")
    public CommonResponse<GroupDto> createGroup(@RequestBody CreateGroupRequestDto requestDto) {

        final GroupDto responseDto = GroupDto.of(groupService.createGroup(requestDto.toVo()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/group/keyword")
    public CommonResponse<RecommendGroupDto> recommendGroup(@RequestParam String keyword) {

        final RecommendGroupDto responseDto = RecommendGroupDto.of(groupService.recommendGroup(keyword));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

}
