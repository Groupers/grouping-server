package com.covengers.grouping.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {

    TEXT("TEXT");
//    EMOTICON("EMOTICON"),
//    IMAGE("IMAGE"),
//    VIDEO("VIDEO"),
//    SOUND("SOUND");
    private final String value;
}
