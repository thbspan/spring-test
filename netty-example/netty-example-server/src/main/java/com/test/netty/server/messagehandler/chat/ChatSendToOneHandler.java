package com.test.netty.server.messagehandler.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.netty.common.codec.Invocation;
import com.test.netty.common.dispatcher.MessageHandler;
import com.test.netty.common.message.chat.ChatRedirectToUserRequest;
import com.test.netty.common.message.chat.ChatSendResponse;
import com.test.netty.common.message.chat.ChatSendToOneRequest;
import com.test.netty.server.server.NettyChannelManager;

import io.netty.channel.Channel;

@Component
public class ChatSendToOneHandler implements MessageHandler<ChatSendToOneRequest> {

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Override
    public void execute(Channel channel, ChatSendToOneRequest message) {
        ChatSendResponse sendResponse = new ChatSendResponse();
        sendResponse.setCode(0);
        sendResponse.setMsgId(message.getMsgId());
        channel.writeAndFlush(new Invocation(ChatSendResponse.TYPE, sendResponse));

        // 创建转发消息，发送给指定的用户
        ChatRedirectToUserRequest sendToUserRequest = new ChatRedirectToUserRequest();
        sendToUserRequest.setMsgId(message.getMsgId());
        sendToUserRequest.setContent(message.getContent());
        nettyChannelManager.send(message.getToUser(), new Invocation(ChatRedirectToUserRequest.TYPE, sendToUserRequest));
    }

    @Override
    public String getType() {
        return ChatSendToOneRequest.TYPE;
    }
}
