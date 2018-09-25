package com.suichen.utils.spi.impl;

import com.suichen.utils.spi.ISayName;

public class SayEnglishName implements ISayName {
    @Override
    public void say() {
        System.out.println("Hello");
    }
}
