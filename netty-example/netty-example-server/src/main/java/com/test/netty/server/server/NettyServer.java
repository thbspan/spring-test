package com.test.netty.server.server;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.netty.server.NettyServerProperties;
import com.test.netty.server.server.handler.NettyServerHandlerInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Component
public class NettyServer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final NettyServerProperties serverProperties;

    @Autowired
    private NettyServerHandlerInitializer nettyServerHandlerInitializer;

    /**
     * bosss 线程组，用于服务端接受客户端的连接
     */
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();

    /**
     * worker线程组，用于服务端接受客户端的读写请求
     */
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    /**
     * Netty Server Channel
     */
    private Channel channel;

    public NettyServer(NettyServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @PostConstruct
    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(serverProperties.getPort())
                // 服务端 accept 队列的大小
                .option(ChannelOption.SO_BACKLOG, 1024)
                // TCP Keepalive 机制，实现 TCP 层级的心跳保活功能
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 允许较小的数据包的发送，降低延迟
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(nettyServerHandlerInitializer);

        ChannelFuture channelFuture = bootstrap.bind().sync();
        if (channelFuture.isSuccess()) {
            channel = channelFuture.channel();
            logger.info("netty server was started on port {}", serverProperties.getPort());
        }
    }

    @PreDestroy
    public void shutdown() {
        if (channel != null) {
            channel.close();
        }
        // 优雅关闭
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
