package com.suichen.utils.seata;

import com.suichen.utils.NamedThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DaemonThreadTest {
    private static ExecutorService executorService = new ThreadPoolExecutor(1,
            1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(),
            new NamedThreadFactory("batchLoggerPrint", 1, true));
    private static BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws Exception{
        executorService.submit(new BatchLogRunnable());
        int count = 100;
        while (count >= 0) {
            count--;
            Thread.sleep(1000*5);
            logQueue.put("it is "+count);
        }

        Thread.sleep(1000*60);
    }

    static class BatchLogRunnable implements Runnable {
        @Override
        public void run() {
            List<String> logList = new ArrayList<>();
            while (true) {
                try {
                    logList.add(logQueue.take());
                    logQueue.drainTo(logList, 1024);
                    for (String str : logList) {
                        System.out.println(str);
                    }

                    logList.clear();
                    TimeUnit.MILLISECONDS.sleep(5L);
                } catch (InterruptedException exx) {
                }

            }
        }
    }
}
