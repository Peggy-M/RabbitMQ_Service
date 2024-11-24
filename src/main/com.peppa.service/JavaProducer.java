package main.com.peppa.service;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class JavaProducer {
    private final static String QUEUE_NAME = "model_training_request";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.120.25.129"); // 修改为 RabbitMQ 的主机地址
        factory.setUsername("peppa"); // 修改为 RabbitMQ 的主机地址
        factory.setPassword("xpy3464.."); // 修改为 RabbitMQ 的主机地址
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            
            String message = "Start model training with parameters: {\"epochs\": 10, \"batchSize\": 100}";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
