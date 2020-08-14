package com.suichen.utils.seata.test;

public interface Callback<T> {
    T execute() throws Throwable;
}
