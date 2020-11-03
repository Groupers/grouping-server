package com.covengers.grouping.controller;

import com.covengers.grouping.dto.*;
import org.graalvm.compiler.nodes.memory.MemoryCheckpoint;
import org.springframework.web.bind.annotation.*;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.service.GroupService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GroupController {

    private final GroupService groupService;
    private final CommonResponseMaker commonResponseMaker;

    @PostMapping("/group")
    public CommonResponse<GroupDto> createGroup(@RequestBody CreateGroupRequestDto requestDto) {

        final GroupDto responseDto = GroupDto.of(groupService.createGroup(requestDto.toVo()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @PostMapping(value = "/group-image")
    public CommonResponse<GroupImageDto> uploadGroupImage(
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam final Long groupId) throws IOException {
        final GroupImageDto responseDto = GroupImageDto.of(groupService.uploadGroupImage(imageFile,groupId));
        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/group/keyword")
    public CommonResponse<RecommendGroupDto> recommendGroup(@RequestParam String keyword) {

        final RecommendGroupDto responseDto = RecommendGroupDto.of(groupService.recommendGroup(keyword));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

}
