package com.covengers.grouping.controller;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.dto.SearchListResultDto;
import com.covengers.grouping.dto.SearchResponseDto;
import com.covengers.grouping.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class KeywordController extends AppApiV1Controller {
    private final KeywordService keywordService;
    private final CommonResponseMaker commonResponseMaker;

    @GetMapping("/keywords/{groupingUserId}/search/history")
    public CommonResponse<SearchListResultDto> getSearchList(@PathVariable("groupingUserId") String groupingUserId) {

        final SearchListResultDto responseDto =
                SearchListResultDto.of(keywordService.getSearchList(groupingUserId));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

}
