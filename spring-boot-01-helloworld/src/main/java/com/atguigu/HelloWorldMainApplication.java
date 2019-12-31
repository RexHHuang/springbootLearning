package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 此注解@SpringBootApplication来标注一个主程序类，说明这是一个springBoot应用
 */
@SpringBootApplication
public class HelloWorldMainApplication {

    public static void main(String[] args) {

        //spring应用启动起来
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }
}
