package com.test.netty.common.codec;

import com.alibaba.fastjson.JSON;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器
 */
public class InvocationEncoder extends MessageToByteEncoder<Invocation> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Invocation msg, ByteBuf out) throws Exception {
        byte[] content = JSON.toJSONBytes(msg);
        // 写入消息长度
        out.writeInt(content.length);
        // 写入内容
        out.writeBytes(content);
    }
}
