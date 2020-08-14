package com.suichen.utils.netty;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private volatile Timer failTimer = null;

    public static void main(String[] args) {
        Application app = new Application();
    }

    private void doInvoke() {
        throw new RuntimeException("故意抛出异常");
    }

    private void addFailed(Runnable task) {
        if (failTimer == null) {
            synchronized (this) {
                if (failTimer == null) {
                    failTimer = new HashedWheelTimer();
                }
            }
        }
    }
    public void invoke() {

    }
}
