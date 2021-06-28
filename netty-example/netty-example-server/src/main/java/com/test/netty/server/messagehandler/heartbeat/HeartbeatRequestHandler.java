package com.test.netty.server.messagehandler.heartbeat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.test.netty.common.codec.Invocation;
import com.test.netty.common.dispatcher.Message;
import com.test.netty.common.dispatcher.MessageHandler;
import com.test.netty.common.message.heartbeat.HeartbeatRequest;
import com.test.netty.common.message.heartbeat.HeartbeatResponse;

import io.netty.channel.Channel;

@Component
public class HeartbeatRequestHandler implements MessageHandler<HeartbeatRequest> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(Channel channel, HeartbeatRequest message) {
        logger.info("server receive Heartbeat request");
        channel.writeAndFlush(new Invocation(HeartbeatResponse.TYPE, new HeartbeatResponse()));
    }

    @Override
    public String getType() {
        return HeartbeatRequest.TYPE;
    }
}
