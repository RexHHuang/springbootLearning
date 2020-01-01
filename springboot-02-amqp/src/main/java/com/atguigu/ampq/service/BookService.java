package com.atguigu.ampq.service;

import com.atguigu.ampq.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy(false)
public class BookService {

    //这个注解没有起作用，打印不出消息来，具体原因目前不明
    @RabbitListener(queues = "atguigu.news")
    public void receive(Book book){
        System.out.println("收到消息：" + book);
    }

    @RabbitListener(queues = "atguigu")
    public void receive02(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
