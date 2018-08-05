package com.suichen.utils.design.strategy;

public class Addition implements Strategy{//实现算法接口
    @Override
    public int calculate(int a, int b) {
        return a+b;
    }
}
