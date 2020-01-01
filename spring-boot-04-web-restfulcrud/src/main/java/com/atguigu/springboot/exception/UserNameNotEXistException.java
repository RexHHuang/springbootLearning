package com.atguigu.springboot.exception;

public class UserNameNotEXistException extends RuntimeException {
    public UserNameNotEXistException() {
        super("用户名不存在异常");
    }
}
