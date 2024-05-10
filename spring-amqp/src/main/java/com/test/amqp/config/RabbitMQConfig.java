package com.test.amqp.config;


public class RabbitMQConfig {

    /**
     * 队列主题名称 direct
     */
    public static final String RABBITMQ_DEMO_TOPIC = "rabbitmq-demo-direct";

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

    // ---- topic ----
    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机名称
     */
    public static final String TOPIC_EXCHANGE_DEMO_NAME = "rabbitmq-demo-topic-exchange";

    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机的队列A的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_A = "rabbitmq-demo-topic-a";

    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机的队列B的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_B = "rabbitmq-demo-topic-b";

    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机的队列C的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_C = "rabbitmq-demo-topic-c";

    // ---- header ----
    /**
     * HEADERS_EXCHANGE交换机名称
     */
    public static final String HEADERS_EXCHANGE_DEMO_NAME = "rabbitmq-demo-headers-exchange";

    /**
     * RabbitMQ的HEADERS_EXCHANGE交换机的队列A的名称
     */
    public static final String HEADERS_EXCHANGE_QUEUE_A = "rabbitmq-demo-headers-a";

    /**
     * RabbitMQ的HEADERS_EXCHANGE交换机的队列B的名称
     */
    public static final String HEADERS_EXCHANGE_QUEUE_B = "rabbitmq-demo-headers-b";
}
