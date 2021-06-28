package com.test.netty.client.messagehandler.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.test.netty.common.dispatcher.MessageHandler;
import com.test.netty.common.message.auth.AuthResponse;

import io.netty.channel.Channel;

@Component
public class AuthResponseHandler implements MessageHandler<AuthResponse> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(Channel channel, AuthResponse message) {
        logger.info("auth result={}", message);
    }

    @Override
    public String getType() {
        return AuthResponse.TYPE;
    }
}
