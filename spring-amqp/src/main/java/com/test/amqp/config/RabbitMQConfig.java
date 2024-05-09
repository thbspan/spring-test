package com.test.amqp.config;


public class RabbitMQConfig {

    /**
     * 队列主题名称
     */
    public static final String RABBITMQ_DEMO_TOPIC = "rabbitmq-demo-topic";

    /**
     * direct 交换机名称
     */
    public static final String RABBITMQ_DEMO_DIRECT_EXCHANGE = "rabbitmq-demo-direct-exchange";

    /**
     * direct 交换机和队列绑定路由键 key
     */
    public static final String RABBITMQ_DEMO_DIRECT_ROUTING = "rabbitmq-demo-direct-routing";
}
