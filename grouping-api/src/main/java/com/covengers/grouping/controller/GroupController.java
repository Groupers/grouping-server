package com.covengers.grouping.controller;

import java.io.IOException;

import com.covengers.grouping.component.RequestContextHelper;
import com.covengers.grouping.dto.*;
import com.covengers.grouping.service.KeywordService;

import com.covengers.grouping.vo.GroupingUserInfoVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.covengers.grouping.component.CommonResponseMaker;
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
    private final RequestContextHelper requestContextHelper;

    @PostMapping("/group")
    public CommonResponse<GroupDto> createGroup(@RequestBody CreateGroupRequestDto requestDto) {

        final GroupingUserInfoVo groupingUserInfoVo = requestContextHelper.getGroupingUserInfoVo();

        final GroupDto responseDto = GroupDto.of(
                groupService.createGroup(requestDto.toVo(groupingUserInfoVo.getGroupingUserId())));

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
    public CommonResponse<RecommendGroupDto> recommendGroup(@RequestParam String keyword) {

        final GroupingUserInfoVo groupingUserInfoVo = requestContextHelper.getGroupingUserInfoVo();

        keywordService.addSearchHistory(groupingUserInfoVo.getGroupingUserId(), keyword);

        final RecommendGroupDto responseDto = RecommendGroupDto.of(groupService.recommendGroup(keyword));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/group/chatrooms")
    public CommonResponse<GroupChatRoomListResponseDto> getGroupChatRoomList(@RequestParam final Long groupId) {

        final GroupChatRoomListResponseDto responseDto =
                GroupChatRoomListResponseDto.of(groupService.getGroupChatRoomList(groupId));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

}
