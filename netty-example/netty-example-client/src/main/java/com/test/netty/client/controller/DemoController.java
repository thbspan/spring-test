package com.test.netty.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.netty.client.client.NettyClient;
import com.test.netty.common.codec.Invocation;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private NettyClient nettyClient;

    @PostMapping("/mock")
    public String mock(@RequestBody Invocation invocation) {
        // 发送消息
        nettyClient.send(invocation);
        return "success";
    }
}
