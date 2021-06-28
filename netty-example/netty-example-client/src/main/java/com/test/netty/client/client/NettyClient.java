package com.test.netty.client.client;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.netty.client.NettyClientProperties;
import com.test.netty.client.client.handler.NettyClientHandlerInitializer;
import com.test.netty.common.codec.Invocation;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

@Component
public class NettyClient {

    /**
     * 重连频率（秒）
     */
    private static final Integer RECONNECT_SECONDS = 20;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final NettyClientProperties clientProperties;
    /**
     * 线程组，用于客户端对服务端的连接、数据读写
     */
    private final EventLoopGroup eventGroup = new NioEventLoopGroup();
    private final Bootstrap bootstrap = new Bootstrap();
    /**
     * Netty client channel
     */
    private volatile Channel channel;

    @Autowired
    private NettyClientHandlerInitializer nettyClientHandlerInitializer;

    public NettyClient(NettyClientProperties clientProperties) {
        this.clientProperties = clientProperties;
    }

    @PostConstruct
    public void start() {
        bootstrap.group(eventGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(clientProperties.getServerHost(), clientProperties.getServerPort())
                .option(ChannelOption.SO_KEEPALIVE, true)
                // 允许发送较小的数据包
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(nettyClientHandlerInitializer);
        connect();
    }

    public void connect() {
        bootstrap.connect().addListener((ChannelFuture future) -> {
            if (!future.isSuccess()) {
                logger.error("Netty client connect failed. reconnect in next {}s", RECONNECT_SECONDS, future.cause());
                reconnect();
                return;
            }
            // 连接成功
            channel = future.channel();
            logger.info("Netty client connect success");
        });
    }

    /**
     * 重连服务端
     */
    public void reconnect() {
        channel = null;
        eventGroup.schedule(() -> {
            logger.info("start reconnect");
            connect();
        }, RECONNECT_SECONDS, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void shutdown() {
        if (channel != null) {
            channel.close();
        }
        eventGroup.shutdownGracefully();
    }

    /**
     * 发送消息
     */
    public void send(Invocation invocation) {
        if (channel == null) {
            logger.error("The connection does not exist");
            return;
        }
        if (!channel.isActive()) {
            logger.error("Connection({}) not active", channel.id());
            return;
        }
        // 发送消息
        channel.writeAndFlush(invocation);
    }
}
