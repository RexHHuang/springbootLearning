package com.atguigu.springboot.controller;

import com.atguigu.springboot.exception.UserNameNotEXistException;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.httpserver.HttpsServerImpl;

//import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 自己定义一个异常处理类，处理各种异常
 */
@ControllerAdvice
public class MyExceptionHandler {

    // 1、浏览器客户端返回的都是json数据，不能自适应
//    @ResponseBody
//    @ExceptionHandler(UserNameNotEXistException.class)
//    public Map<String, Object> handlerException(Exception e){
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "404code");
//        map.put("message", e.getMessage());
//        return map;
//    }

//    @ExceptionHandler(UserNameNotEXistException.class)
//    public String handlerException(Exception e, HttpServletRequest request){
//        request.setAttribute("javax.servlet.error.status_code", 500);
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "404code");
//        map.put("message", e.getMessage());
//        request.setAttribute("ext", map);
//        //转发到/error
//        return "forward:/error";
//    }
}
