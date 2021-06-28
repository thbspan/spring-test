package com.test.netty.common.message.heartbeat;

import com.test.netty.common.dispatcher.Message;

public class HeartbeatRequest implements Message {
    public static final String TYPE = "HEARTBEAT_REQUEST";

    @Override
    public String toString() {
        return "HeartbeatRequest{}";
    }
}
