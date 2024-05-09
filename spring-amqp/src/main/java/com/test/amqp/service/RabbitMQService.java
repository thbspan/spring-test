package com.test.amqp.service;

public interface RabbitMQService {

    String sendMsg(String msg) throws Exception;
}
