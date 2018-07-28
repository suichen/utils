package com.suichen.utils.cache;

import com.google.common.cache.*;
import com.google.common.cache.Cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*LoadingCache<Integer, String> strCache = CacheBuilder.newBuilder()
                                                    .concurrencyLevel(8)
                                                    .expireAfterWrite(8, TimeUnit.SECONDS)
                                                    .initialCapacity(10)
                                                    .maximumSize(100)
                                                    .recordStats()
                                                    .removalListener(new RemovalListener<Object, Object>() {
                                                        @Override
                                                        public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
                                                            System.out.println(removalNotification.getKey()+" was removed, cause is "+
                                                                            removalNotification.getCause());
                                                        }
                                                    }).build(
                        new CacheLoader<Integer, String>() {
                            @Override
                            public String load(Integer key) throws Exception {
                                System.out.println("load data: "+key);
                                String str = key + ":cache-value";
                                return str;
                            }
                        }
                );


        for (int i = 0; i < 20; i++) {
            String str = strCache.get(1);
            System.out.println(str);

            TimeUnit.SECONDS.sleep(1);
        }


        System.out.println("cache status.");
        System.out.println(strCache.stats().toString());*/


        Cache<Object, Object> cache
                = CacheBuilder.newBuilder().maximumSize(1000).build();

        Object resultVal = cache.get("test", new Callable<String>() {
            @Override
            public String call() {
                String strProValue = "test-value";
                return strProValue;
            }
        });

    }
}
