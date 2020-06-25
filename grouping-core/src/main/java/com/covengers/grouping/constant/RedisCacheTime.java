package com.covengers.grouping.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RedisCacheTime {
    SIGN_UP_PHONE_NUMBER(60),
    SIGN_UP_EMAIL(60);

    private final Integer cacheTime;
}
