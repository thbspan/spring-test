package com.test.amqp.consumer;

import com.test.amqp.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FanoutExchangeConsumerB {

    @RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.FANOUT_EXCHANGE_QUEUE_TOPIC_B))
    public void process(Map<String, Object> map) {
        Thread currentThread = Thread.currentThread();
        System.out.println("[RabbitMQConsumer.onMessage] - [线程名称: " + currentThread.getName() + "] - [线程ID: " + currentThread.threadId() + "] - 队列B收到消息：" + map.toString());
    }
}
