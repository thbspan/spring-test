package com.test.amqp.controller;

import com.test.amqp.service.RabbitMQService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {
    @Resource
    private RabbitMQService rabbitMQService;

    /**
     * 发送消息
     */
    @PostMapping("/send-msg")
    public String sendMsg(@RequestParam(name = "msg") String msg,
                          @RequestParam(name = "exchange", required = false, defaultValue = "direct") String exchange,
                          @RequestParam(name = "routing", required = false) String routingKey) throws Exception {
        return switch (exchange) {
            case "fanout" -> rabbitMQService.sendMsgByFanoutExchange(msg);
            case "topic" -> rabbitMQService.sendMsgByTopicExchange(msg, routingKey);
            default -> rabbitMQService.sendMsg(msg);
        };
    }
}