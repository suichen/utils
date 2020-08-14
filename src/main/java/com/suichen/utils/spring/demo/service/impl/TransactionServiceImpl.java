package com.suichen.utils.spring.demo.service.impl;

import com.suichen.utils.spring.demo.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public void insertData() {
        //mybatis 更新语句1
        //mybatis 更新语句2
        //mybatis 删除语句1
        //mybatis 插入语句1

        //hibernate 删除语句1
    }
}
