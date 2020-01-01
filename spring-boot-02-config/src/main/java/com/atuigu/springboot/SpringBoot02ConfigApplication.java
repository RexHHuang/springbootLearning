package com.atuigu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * 给容器中导入配置文件@ImportResource(locations = {"classpath:applicationContext.xml"})
 * 不建议这样做
 * springBoot推荐给容器中添加组件的方式：推荐使用全注解的方式
 *      1、配置类
 */
//@ImportResource(locations = {"classpath:applicationContext.xml"})
@SpringBootApplication
public class SpringBoot02ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot02ConfigApplication.class, args);
    }

}
