package com.test.amqp.consumer;

import com.test.amqp.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
//使用queuesToDeclare属性，如果不存在则会创建队列
@RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.RABBITMQ_DEMO_TOPIC))
public class RabbitMQConsumer {

    @RabbitHandler
    public void onMessage(Map<String, Object> msg) {
        Thread currentThread = Thread.currentThread();
        System.out.println("[RabbitMQConsumer.onMessage] - [线程名称: " + currentThread.getName() + "] - [线程ID: " + currentThread.threadId() + "] - [接收消息内容:" + msg + "]");
    }
}
