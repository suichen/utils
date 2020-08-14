package com.suichen.utils.spring.aop;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Override
    public void saveOrder() {
        System.out.println("save order....");
    }
}
