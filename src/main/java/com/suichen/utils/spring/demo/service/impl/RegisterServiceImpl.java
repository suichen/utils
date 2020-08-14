package com.suichen.utils.spring.demo.service.impl;


import com.suichen.utils.spring.annotation.MyAutowired;
import com.suichen.utils.spring.annotation.MyService;
import com.suichen.utils.spring.demo.repository.RegisterDao;
import com.suichen.utils.spring.demo.service.RegisterService;

/**
 * Created by Xiao Liang on 2018/6/27.
 */
@MyService
public class RegisterServiceImpl implements RegisterService {

    @MyAutowired
    private RegisterDao registerDao;

    @Override
    public void register() {
        registerDao.register();
    }
}
