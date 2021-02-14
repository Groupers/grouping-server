package com.covengers.grouping.domain;

import com.covengers.grouping.constant.MessageType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String topicId;

    private String publisher;

    private MessageType messageType;

    private String message;
}
