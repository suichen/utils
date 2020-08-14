package com.suichen.utils.spring.demo.repository;


import com.suichen.utils.spring.dataObject.User;

/**
 * @ClassName UserMapper
 * @Description
 * @Data 2018/7/7
 * @Author xiao liang
 */
public interface UserMapper {

    User queryUser(String userName, String passWord);

}
