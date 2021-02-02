package com.covengers.grouping.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.covengers.grouping.adapter.api.GroupingApiClient;
import com.covengers.grouping.dto.GroupingUserDto;
import com.covengers.grouping.dto.SignUpRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final GroupingApiClient groupingApiClient;

    @Transactional
    public GroupingUserDto completeSignUp(SignUpRequestDto requestVo) {

        return groupingApiClient.completeSignUp(requestVo).getData();
    }
}
