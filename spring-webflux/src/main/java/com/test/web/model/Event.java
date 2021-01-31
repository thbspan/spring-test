package com.test.web.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Event {
    private long timeStamp;
    private String message;

    public Event(long timeStamp, String message) {
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
