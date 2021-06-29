package com.test.netty.server.messagehandler.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.netty.common.codec.Invocation;
import com.test.netty.common.dispatcher.MessageHandler;
import com.test.netty.common.message.chat.ChatRedirectToUserRequest;
import com.test.netty.common.message.chat.ChatSendResponse;
import com.test.netty.common.message.chat.ChatSendToAllRequest;
import com.test.netty.server.server.NettyChannelManager;

import io.netty.channel.Channel;

@Component
public class ChatSendToAllHandler implements MessageHandler<ChatSendToAllRequest> {

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Override
    public void execute(Channel channel, ChatSendToAllRequest message) {
        ChatSendResponse sendResponse = new ChatSendResponse();
        sendResponse.setMsgId(message.getMsgId());
        sendResponse.setCode(0);
        channel.writeAndFlush(new Invocation(ChatSendResponse.TYPE, sendResponse));

        // 创建转发消息，并广播发送
        ChatRedirectToUserRequest sendToUserRequest = new ChatRedirectToUserRequest();
        sendToUserRequest.setMsgId(message.getMsgId());
        sendToUserRequest.setContent(message.getContent());
        nettyChannelManager.sendAll(new Invocation(ChatRedirectToUserRequest.TYPE, sendToUserRequest));
    }

    @Override
    public String getType() {
        return ChatSendToAllRequest.TYPE;
    }
}
