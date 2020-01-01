package com.atuigu.springboot.config;

import com.atuigu.springboot.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAppConfig {

    @Bean
    public HelloService helloService(){
        System.out.println("MyAppConfig.helloService...给容器中添加了组件");
        return new HelloService();
    }
}
