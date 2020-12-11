package com.covengers.grouping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ChatRoomVo {

    private final Long id;

    private final String topicId;

    private final String title;
}
