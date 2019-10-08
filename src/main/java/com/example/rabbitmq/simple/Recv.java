package com.example.rabbitmq.simple;

import com.example.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者获取消息
 */
public class Recv {

    private static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 获取连接
        Connection connection = ConnectionUtils.getConnection();

        // 创建频道
        Channel channel = connection.createChannel();

        // 队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("new api recv: " + msg);
            }
        };

        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

//    @SuppressWarnings("deprecation")
//    private static void oldAPI() {
//        // 获取连接
//        Connection connection = ConnectionUtils.getConnection();
//
//        // 创建频道
//        Channel channel = connection.createChannel();
//
//        // 定义队列的消费者
//        QueueingConsumer consumer = new QueueingConsumer(channel);
//
//        // 监听队列
//        channel.basicConsume(QUEUE_NAME, true, consumer);
//        while (true) {
//            Delivery delivery = consumer.nextDelivery();
//            String msgString = new String(delivery.getBody());
//            System.out.println("[recv] msg:" + msgString);
//        }
//    }

}
