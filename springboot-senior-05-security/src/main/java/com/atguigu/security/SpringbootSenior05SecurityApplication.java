package com.atguigu.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1、引入springSecurity
 * 2、编写springSecurity的配置类
 *      extends WebSecurityConfigurerAdapter 同时开启注解 @EnableWebSecurity
 * 3、控制请求的访问权限
 */
@SpringBootApplication
public class SpringbootSenior05SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSenior05SecurityApplication.class, args);
    }

}
