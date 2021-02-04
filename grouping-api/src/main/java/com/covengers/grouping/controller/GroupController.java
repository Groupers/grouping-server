package com.covengers.grouping.controller;

import java.io.IOException;

import com.covengers.grouping.service.KeywordService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.dto.CreateGroupRequestDto;
import com.covengers.grouping.dto.GroupDto;
import com.covengers.grouping.dto.RecommendGroupDto;
import com.covengers.grouping.service.GroupService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GroupController extends AppApiV1Controller {

    private final GroupService groupService;
    private final KeywordService keywordService;
    private final CommonResponseMaker commonResponseMaker;

    @PostMapping("/group")
    public CommonResponse<GroupDto> createGroup(@RequestBody CreateGroupRequestDto requestDto) {

        final GroupDto responseDto = GroupDto.of(groupService.createGroup(requestDto.toVo()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @PostMapping("/group/image")
    public CommonResponse<GroupDto> uploadGroupImage(
            @RequestPart MultipartFile imageFile,
            @RequestParam final Long groupId
    ) throws IOException {

        final GroupDto responseDto = GroupDto.of(groupService.uploadGroupImage(imageFile, groupId));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/group/keyword")
    public CommonResponse<RecommendGroupDto> recommendGroup(@RequestParam Long groupingUserId, @RequestParam String keyword) {

        keywordService.addSearchHistory(groupingUserId, keyword);

        final RecommendGroupDto responseDto = RecommendGroupDto.of(groupService.recommendGroup(keyword));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

}
