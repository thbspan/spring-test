package com.test.netty.server.messagehandler.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.test.netty.common.codec.Invocation;
import com.test.netty.common.dispatcher.MessageHandler;
import com.test.netty.common.message.auth.AuthRequest;
import com.test.netty.common.message.auth.AuthResponse;
import com.test.netty.server.server.NettyChannelManager;

import io.netty.channel.Channel;

@Component
public class AuthRequestHandler implements MessageHandler<AuthRequest> {

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Override
    public void execute(Channel channel, AuthRequest authRequest) {
        if (!StringUtils.hasLength(authRequest.getAccessToken())) {
            AuthResponse authResponse = new AuthResponse();
            authResponse.setCode(1);
            authResponse.setMessage("auth token is missing");
            channel.writeAndFlush(new Invocation(AuthResponse.TYPE, authRequest));
            return;
        }

        // ... do something

        nettyChannelManager.addUser(channel, authRequest.getAccessToken());
        // 响应成功信息
        AuthResponse authResponse = new AuthResponse();
        authResponse.setCode(0);
        authResponse.setMessage("success");
        channel.writeAndFlush(new Invocation(AuthResponse.TYPE, authResponse));
    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }
}
