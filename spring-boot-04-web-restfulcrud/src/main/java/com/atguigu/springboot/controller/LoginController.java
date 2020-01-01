package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
//    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){

        if(!StringUtils.isEmpty(username) && password.equals("123456")){
            //登录成功，防止表单重复提交，可以重定向
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }
        else{
            //登录失败
            map.put("failMsg", "用户名密码错误");
            return "login";
        }
    }
}
