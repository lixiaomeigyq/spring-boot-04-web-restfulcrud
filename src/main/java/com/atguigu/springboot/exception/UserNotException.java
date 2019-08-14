package com.atguigu.springboot.exception;

/**
 * Created by Administrator on 2019/8/13.
 */
public class UserNotException  extends RuntimeException {
    public UserNotException() {
        super("用户不存在");
    }
}
