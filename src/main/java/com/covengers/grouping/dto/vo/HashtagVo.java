package com.covengers.grouping.dto.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class HashtagVo {
    private final Long id;

    private final String hashtag;
}
