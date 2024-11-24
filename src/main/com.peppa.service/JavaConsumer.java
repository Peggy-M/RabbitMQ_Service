package main.com.peppa.service;

import com.rabbitmq.client.*;

import java.io.IOException;

public class JavaConsumer {
    private final static String RESPONSE_QUEUE = "model_training_response";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.120.25.129"); // 修改为 RabbitMQ 的主机地址
        factory.setUsername("peppa"); // 修改为 RabbitMQ 的主机地址
        factory.setPassword("xpy3464.."); // 修改为 RabbitMQ 的主机地址
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(RESPONSE_QUEUE, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received: '" + message + "'");
        };

        channel.basicConsume(RESPONSE_QUEUE, true, deliverCallback, consumerTag -> { });
    }
}
