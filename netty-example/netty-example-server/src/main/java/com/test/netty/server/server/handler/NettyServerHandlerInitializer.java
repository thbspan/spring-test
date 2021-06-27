package com.test.netty.server.server.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

@Component
public class NettyServerHandlerInitializer extends ChannelInitializer<Channel> {

    /**
     * 心跳超时时间
     */
    private static final Integer READ_TIMEOUT_SECONDS = 3 * 60;
    @Autowired
    private NettyServerHandler nettyServerHandler;

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                // 服务端处理器
                .addLast(nettyServerHandler);
    }
}
