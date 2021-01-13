package com.covengers.grouping.controller;

import com.covengers.grouping.component.CommonResponseMaker;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.dto.SearchHistoryListResultDto;
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
    public CommonResponse<SearchHistoryListResultDto> getSearchHistoryList(@PathVariable("groupingUserId") String groupingUserId) {

        final SearchHistoryListResultDto responseDto =
                SearchHistoryListResultDto.of(keywordService.getSearchHistoryList(groupingUserId));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/keywords/search/trends")
    public CommonResponse<SearchTrendsListResultDto> getSearchTrendsList() {

        final SearchTrendsListResultDto responseDto =
                SearchTrendsListResultDto.of(keywordService.getSearchTrendsList());

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

}
