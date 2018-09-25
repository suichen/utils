package com.suichen.utils.spi.impl;

import com.suichen.utils.spi.MyLog;

public class LogA implements MyLog {
    @Override
    public void log() {
        System.out.println("my is logA");
    }
}
