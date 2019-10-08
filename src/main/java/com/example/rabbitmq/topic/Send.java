package com.example.rabbitmq.topic;

import com.example.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic"); // 分发

        // 发送消息
        String msgString = "商品....";

        String routingKey = "info";
        channel.basicPublish(EXCHANGE_NAME, "goods.delete", null, msgString.getBytes());

        System.out.println("send: " + msgString);

        channel.close();
        connection.close();
    }
}
