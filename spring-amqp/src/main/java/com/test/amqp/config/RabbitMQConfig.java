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

    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的队列 A 的名称
     */
    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_A = "rabbitmq-demo-fanout-a";

    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的队列 B 的名称
     */
    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_B = "rabbitmq-demo-fanout-b";

    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的名称
     */
    public static final String FANOUT_EXCHANGE_DEMO_NAME = "rabbitmq-demo-fanout-exchange";
}
