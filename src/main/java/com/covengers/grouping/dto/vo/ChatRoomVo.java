package com.covengers.grouping.dto.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ChatRoomVo {
    private final Long id;
    private final String name;
    private final String imagePath;
}
