package com.test.netty.common.dispatcher;

import io.netty.channel.Channel;

public interface MessageHandler<T extends Message> {

    /**
     * 执行处理消息
     */
    void execute(Channel channel, T message);

    String getType();
}
