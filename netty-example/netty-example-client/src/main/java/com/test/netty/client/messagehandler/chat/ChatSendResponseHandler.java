package com.test.netty.client.messagehandler.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.test.netty.common.dispatcher.MessageHandler;
import com.test.netty.common.message.chat.ChatSendResponse;

import io.netty.channel.Channel;

@Component
public class ChatSendResponseHandler implements MessageHandler<ChatSendResponse> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(Channel channel, ChatSendResponse message) {
        logger.info("消息发送结果：{}", message);
    }

    @Override
    public String getType() {
        return ChatSendResponse.TYPE;
    }
}
