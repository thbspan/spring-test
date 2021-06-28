package com.test.netty.common.message.heartbeat;

import com.test.netty.common.dispatcher.Message;

public class HeartbeatResponse implements Message {
    public static final String TYPE = "HEARTBEAT_RESPONSE";

    @Override
    public String toString() {
        return "HeartbeatResponse{}";
    }
}
