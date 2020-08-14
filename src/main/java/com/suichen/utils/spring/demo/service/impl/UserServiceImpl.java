package com.suichen.utils.spring.demo.service.impl;


import com.suichen.utils.spring.annotation.MyAutowired;
import com.suichen.utils.spring.annotation.MyService;
import com.suichen.utils.spring.dataObject.User;
import com.suichen.utils.spring.demo.repository.RegisterDao;
import com.suichen.utils.spring.demo.repository.UserDao;
import com.suichen.utils.spring.demo.repository.UserMapper;
import com.suichen.utils.spring.demo.service.UserService;

/**
 * Created by Xiao Liang on 2018/6/27.
 */
@MyService
public class UserServiceImpl implements UserService {

    @MyAutowired
    private UserDao userDao;

    @MyAutowired
    private RegisterDao registerDao;

    /*********************************/
    @MyAutowired
    private UserMapper userMapper;
    /*********************************/

    @Override
    public User queryUser(String userName, String passWord) {
        return userMapper.queryUser(userName,passWord);
    }
}
