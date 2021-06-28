package com.test.netty.client.messagehandler.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.test.netty.common.dispatcher.MessageHandler;
import com.test.netty.common.message.chat.ChatRedirectToUserRequest;

import io.netty.channel.Channel;

@Component
public class ChatRedirectToUserRequestHandler implements MessageHandler<ChatRedirectToUserRequest> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(Channel channel, ChatRedirectToUserRequest message) {
        logger.info("收到消息：{}", message);
    }

    @Override
    public String getType() {
        return ChatRedirectToUserRequest.TYPE;
    }
}
