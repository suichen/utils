package com.suichen.utils.design.strategy;

public class Multiply implements Strategy{
    @Override
    public int calculate(int a, int b) {
        return a*b;
    }
}
