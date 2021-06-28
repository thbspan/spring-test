package com.test.netty.client.messagehandler.heartbeat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.test.netty.common.dispatcher.Message;
import com.test.netty.common.dispatcher.MessageHandler;
import com.test.netty.common.message.heartbeat.HeartbeatResponse;

import io.netty.channel.Channel;

@Component
public class HeartbeatResponseHandler implements MessageHandler<HeartbeatResponse> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(Channel channel, HeartbeatResponse message) {
        logger.info("client receive Heartbeat response");
    }

    @Override
    public String getType() {
        return HeartbeatResponse.TYPE;
    }
}
