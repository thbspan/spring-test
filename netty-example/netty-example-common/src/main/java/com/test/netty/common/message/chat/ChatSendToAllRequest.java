package com.test.netty.common.message.chat;

import com.test.netty.common.dispatcher.Message;

public class ChatSendToAllRequest implements Message {
    public static final String TYPE = "CHAT_SEND_TO_ALL_REQUEST";

    private String msgId;
    private String content;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ChatSendToAllRequest{" +
                "msgId='" + msgId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
