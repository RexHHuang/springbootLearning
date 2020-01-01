package com.atguigu.springboot.controller;

import com.atguigu.springboot.exception.UserNameNotEXistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {


//    @RequestMapping(value = {"/", "/login.html"})
//    public String loginPage(){
//        return "login";
//    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("username") String username){
        if (username.equals("aaa")){
            throw new UserNameNotEXistException();
        }
        return "Hello, World!";
    }

    @RequestMapping("/success")
    public String success(Map<String, Object> map){

        map.put("hello", "<h1>你好啊！</h1>");
        map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
        //thymeleft默认在classpath:/templates/success.html来找
        return "success";
    }

}
