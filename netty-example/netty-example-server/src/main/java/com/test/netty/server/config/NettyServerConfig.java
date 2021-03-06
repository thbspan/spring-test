package com.test.netty.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.netty.common.dispatcher.MessageDispatcher;
import com.test.netty.common.dispatcher.MessageHandlerContainer;

@Configuration
public class NettyServerConfig {

    @Bean
    public MessageDispatcher messageDispatcher() {
        return new MessageDispatcher(messageHandlerContainer());
    }

    @Bean
    public MessageHandlerContainer messageHandlerContainer() {
        return new MessageHandlerContainer();
    }
}
