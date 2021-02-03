package com.covengers.grouping.component;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.covengers.grouping.constant.RequestHeaders;
import com.covengers.grouping.vo.GroupingUserInfoVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestContextHelper {

    public GroupingUserInfoVo getGroupingUserVo() {
        return GroupingUserInfoVo.builder()
                                 .groupingUserId(getValueFromHeaders(RequestHeaders.GROUPING_USER_ID))
                                 .build();
    }

    private String getValueFromHeaders(String headerName) {
        return ((ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes()))
                .getRequest()
                .getHeader(headerName);
    }
}
