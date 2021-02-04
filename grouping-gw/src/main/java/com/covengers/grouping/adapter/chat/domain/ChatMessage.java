package com.covengers.grouping.adapter.chat.domain;

import com.covengers.grouping.constant.MessageType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String topicId;

    private MessageType type;

    private String publisher;

    private String content;
}
