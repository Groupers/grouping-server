package com.covengers.grouping.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {

    TEXT("TXT", "TEXT"),
    EMOTICON("EMO", "EMOTICON"),
    PICTURE("PIC", "PICTURE"),
    VIDEO("VID", "VIDEO"),
    SOUND("SOD", "SOUND");

    private final String type;
    private final String value;
}
