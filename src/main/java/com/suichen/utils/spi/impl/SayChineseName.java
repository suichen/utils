package com.suichen.utils.spi.impl;

import com.suichen.utils.spi.ISayName;

public class SayChineseName implements ISayName {
    @Override
    public void say() {
        System.out.println("哈喽");
    }
}
