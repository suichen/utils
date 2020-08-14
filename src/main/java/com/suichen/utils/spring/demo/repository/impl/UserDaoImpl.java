package com.suichen.utils.spring.demo.repository.impl;


import com.suichen.utils.spring.annotation.MyRepository;
import com.suichen.utils.spring.demo.repository.UserDao;

/**
 * Created by Xiao Liang on 2018/6/27.
 */
@MyRepository
public class UserDaoImpl implements UserDao {

    @Override
    public void test() {
        System.out.println("我是UserDao");
    }
}
