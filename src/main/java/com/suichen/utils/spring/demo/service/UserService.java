package com.suichen.utils.spring.demo.service;


import com.suichen.utils.spring.dataObject.User;

/**
 * Created by Xiao Liang on 2018/6/27.
 */
public interface UserService {

     User queryUser(String userName, String passWord);
}
