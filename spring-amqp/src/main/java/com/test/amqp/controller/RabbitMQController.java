package com.test.amqp.controller;

import com.test.amqp.service.RabbitMQService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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
                          @RequestParam(name = "routing", required = false) String routingKey,
                          HttpServletRequest request) throws Exception {
        Map<String, Object> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headers.put(headerName, headerValue);
        }
        return switch (exchange) {
            case "fanout" -> rabbitMQService.sendMsgByFanoutExchange(msg);
            case "topic" -> rabbitMQService.sendMsgByTopicExchange(msg, routingKey);
            case "headers" -> rabbitMQService.sendMsgByHeadersExchange(msg, headers);
            default -> rabbitMQService.sendMsg(msg);
        };
    }
}
