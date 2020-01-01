package com.atguigu.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置
 *  1、RabbitAutoConfiguration
 *  2、有自动配置了连接工厂 CachingConnectionFactory
 *  3、RabbitProperties 封装了 RabbitMQ的所有配置
 *  4、RabbitTemplate 给RabbitMQ发送和接收消息的
 *  5、AmqpAdmin ：RabbitMQ的系统管理组件
 *      这个组件可以帮我们创建、删除 queue Exchange Binding
 *  6、@EnableRabbit + @RabbitListener 监听消息队列的内容
 * 
 */
@EnableRabbit // 开启基于注解的RabbitMQ模式
@SpringBootApplication
public class Springboot02AmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02AmqpApplication.class, args);
    }

}
