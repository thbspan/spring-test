package com.test.netty.server.server;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.util.AttributeKey;

/**
 * 客户端 Channel 管理器
 * 1、客户端 channel 的管理
 * 2、向客户端 channel 发送消息
 */
@Component
public class NettyChannelManager {
    private static final AttributeKey<String> CHANNEL_ATTR_KEY_USER = AttributeKey.newInstance("user");

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Channel 映射
     */
    private final ConcurrentMap<ChannelId, Channel> channels = new ConcurrentHashMap<>();

    /**
     * 用户与 Channel 的映射。
     * <p>
     * 通过它，可以获取用户对应的 Channel。这样，我们可以向指定用户发送消息。
     */
    private final ConcurrentMap<String, Channel> userChannels = new ConcurrentHashMap<>();

    public void add(Channel channel) {
        channels.put(channel.id(), channel);
        logger.info("[add][一个连接({})加入了]", channel.id());
    }

    public void remove(Channel channel) {
        // 移除 channels
        channels.remove(channel.id());
        // 移除 userChannels
        if (channel.hasAttr(CHANNEL_ATTR_KEY_USER)) {
            userChannels.remove(channel.attr(CHANNEL_ATTR_KEY_USER).get());
        }
        logger.info("[remove][一个连接({})离开了]", channel.id());
    }

    /**
     * 添加指定用户到 {@link #userChannels} 中
     */
    public void addUser(Channel channel, String user) {
        Channel existChannel = channels.get(channel.id());
        if (existChannel == null) {
            logger.error("[addUser][连接({}) 不存在]", channel.id());
            return;
        }
        // 设置属性
        channel.attr(CHANNEL_ATTR_KEY_USER).set(user);
        // 添加到 userChannels
        userChannels.put(user, channel);
    }
}
