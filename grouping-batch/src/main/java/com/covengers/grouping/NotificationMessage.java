package com.covengers.grouping;

import lombok.Getter;

@Getter
public class NotificationMessage {

    private final String notificationTitle;
    private final String notificationBody;

    public NotificationMessage() {
        notificationTitle = "title";
        notificationBody = "body";
    }
}
