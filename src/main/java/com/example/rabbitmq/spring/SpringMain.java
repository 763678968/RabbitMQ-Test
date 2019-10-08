package com.example.rabbitmq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

//    @Autowired
//    private RabbitTemplate template;

    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:context.xml");

        // RabbitMQ模板
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);

        // 发送消息
        template.convertAndSend("Hello, world!");
        Thread.sleep(1000); // 休眠1秒
        ctx.destroy();
    }
}
