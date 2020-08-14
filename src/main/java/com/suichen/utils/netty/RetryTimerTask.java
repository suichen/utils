package com.suichen.utils.netty;

import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryTimerTask implements TimerTask {
    private static final Logger log = LoggerFactory.getLogger(RetryTimerTask.class);
    //每隔几秒执行一次
    private final long tick;
    //最大重试次数
    private final int retries;
    private int retryTimes = 0;
    private Runnable task;


    public RetryTimerTask(long tick, int retries, Runnable task) {
        this.tick = tick;
        this.retries = retries;
        this.task = task;
    }

    @Override
    public void run(Timeout timeout) throws Exception {
        try {
            task.run();
        }catch (Throwable e) {
            if ((++retryTimes) >= retries) {
                log.error("失败重试次数");
            }
        }
    }































}
