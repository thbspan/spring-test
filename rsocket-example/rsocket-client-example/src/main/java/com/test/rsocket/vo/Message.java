package com.test.rsocket.vo;

import java.time.Instant;
import java.util.StringJoiner;

public class Message {
    private String from;
    private String to;
    private long index;
    private long created = Instant.now().getEpochSecond();

    public Message() {
    }

    public Message(String from, String to) {
        this.from = from;
        this.to = to;
        this.index = 0;
    }

    public Message(String from, String to, long index) {
        this.from = from;
        this.to = to;
        this.index = index;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Message.class.getSimpleName() + "[", "]")
                .add("from='" + from + "'")
                .add("to='" + to + "'")
                .add("index=" + index)
                .add("created=" + created)
                .toString();
    }
}
