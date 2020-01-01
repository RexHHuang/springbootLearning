package com.atguigu.ticket.service;

import com.alibaba.dubbo.config.annotation.Service;

@org.springframework.stereotype.Service
@Service //暴露服务 将服务发布出去
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "《厉害了，我的国》";
    }
}
