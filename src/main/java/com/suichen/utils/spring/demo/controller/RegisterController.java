package com.suichen.utils.spring.demo.controller;


import com.suichen.utils.spring.annotation.MyAutowired;
import com.suichen.utils.spring.annotation.MyController;
import com.suichen.utils.spring.annotation.MyRequestMapping;
import com.suichen.utils.spring.demo.service.RegisterService;
import com.suichen.utils.spring.demo.service.UserService;

/**
 * Created by Xiao Liang on 2018/6/27.
 */
@MyController
public class RegisterController {

    @MyAutowired
    private UserService userService;

    @MyAutowired
    private RegisterService registerService;

    @MyRequestMapping("/register")
    public void regeister(){
        userService.queryUser("","");
        registerService.register();
    }

}
