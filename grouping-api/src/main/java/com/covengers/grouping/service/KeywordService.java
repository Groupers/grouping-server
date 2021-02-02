package com.covengers.grouping.service;

import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.domain.GroupingUser;
import com.covengers.grouping.domain.Keyword;
import com.covengers.grouping.exception.CommonException;
import com.covengers.grouping.repository.GroupingUserRepository;
import com.covengers.grouping.repository.KeywordRepository;
import com.covengers.grouping.vo.KeywordVo;
import com.covengers.grouping.vo.SearchHistoryListResultVo;
import com.covengers.grouping.vo.SearchTrendsListResultVo;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class KeywordService {
    private final GroupingUserRepository groupingUserRepository;
    private final KeywordRepository keywordRepository;

    public SearchHistoryListResultVo getSearchHistoryList(Long groupingUserId) {

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopById(groupingUserId);

        if (!groupingUserOptional.isPresent()) {
            throw new CommonException(ResponseCode.USER_NOT_EXISTED);
        }

        return SearchHistoryListResultVo.builder()
                .searchHistoryList(groupingUserOptional.get().toSearchHistoryList())
                .build();
    }

    public SearchTrendsListResultVo getSearchTrendsList(int dayPeriod, int keywordCount) {

        final LocalDateTime startPoint = LocalDateTime.now().minusDays(dayPeriod);

        final List<String> keywordList = keywordRepository.findKeywordByCreatedAtAfter(startPoint);

        HashMap<String, Integer> keywordMap = new HashMap<String, Integer>();

        for (String key : keywordList) {
            keywordMap.put(key, keywordMap.getOrDefault(key, 0) + 1);
        }

        List<String> searchTrendsList = new ArrayList(keywordMap.keySet());
        Collections.sort(searchTrendsList, (o1, o2) -> (keywordMap.get(o2).compareTo(keywordMap.get(o1))));

        if (searchTrendsList.size() > keywordCount) {
            searchTrendsList = searchTrendsList.subList(0, keywordCount);
        }

        return SearchTrendsListResultVo.builder()
                .searchTrendsList(searchTrendsList.stream().map(keyword -> KeywordVo.builder()
                        .keyword(keyword)
                        .build())
                        .collect(Collectors.toList())
                )
                .build();
    }

    @Transactional
    public void addSearchHistory(Long groupingUserId, String keyword) {

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopById(groupingUserId);

        GroupingUser groupingUser =
                groupingUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        final Keyword keywordEntity = new Keyword(keyword);

        keywordRepository.save(keywordEntity);

        groupingUser.getSearchHistory().add(keywordEntity);

        return;
    }

}
