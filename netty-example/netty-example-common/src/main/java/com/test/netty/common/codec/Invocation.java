package com.test.netty.common.codec;

import com.alibaba.fastjson.JSON;
import com.test.netty.common.dispatcher.Message;

/**
 * 通信协议的消息体
 */
public class Invocation {
    /**
     * 类型
     */
    private String type;
    /**
     * 消息
     */
    private String message;

    public Invocation() {
    }

    public Invocation(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public Invocation(String type, Message message) {
        this.type = type;
        this.message = JSON.toJSONString(message);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
