package com.test.netty.common.dispatcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSON;
import com.test.netty.common.codec.Invocation;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class MessageDispatcher extends SimpleChannelInboundHandler<Invocation> {

    private final ExecutorService executor = Executors.newFixedThreadPool(200);

    private final MessageHandlerContainer messageHandlerContainer;

    public MessageDispatcher(MessageHandlerContainer messageHandlerContainer) {
        this.messageHandlerContainer = messageHandlerContainer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Invocation msg) throws Exception {
        MessageHandler<Message> messageHandler = messageHandlerContainer.getMessageHandler(msg.getType());
        Class<? extends Message> messageClass = MessageHandlerContainer.getMessageClass(messageHandler);
        Message message = JSON.parseObject(msg.getMessage(), messageClass);
        // 执行逻辑
        executor.submit(() -> {
            // noinspection
            messageHandler.execute(ctx.channel(), message);
        });
    }
}
