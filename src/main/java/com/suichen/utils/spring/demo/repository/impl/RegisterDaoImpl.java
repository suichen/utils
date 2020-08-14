package com.suichen.utils.spring.demo.repository.impl;


import com.suichen.utils.spring.annotation.MyRepository;
import com.suichen.utils.spring.demo.repository.RegisterDao;

/**
 * Created by Xiao Liang on 2018/6/27.
 */
@MyRepository
public class RegisterDaoImpl implements RegisterDao {

    @Override
    public void register() {
        System.out.println("我是RegisterDao");
    }
}
