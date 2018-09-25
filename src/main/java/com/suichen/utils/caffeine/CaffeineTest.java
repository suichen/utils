package com.suichen.utils.caffeine;

import com.github.benmanes.caffeine.cache.*;
import com.google.common.graph.Graph;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class CaffeineTest {
    private static Object createExpensiveGraph(String key) {
        return "";
    }

    public static void main(String[] args) {
        Cache<String, Object> manualCache = Caffeine.newBuilder()
                                                .expireAfterAccess(10, TimeUnit.MINUTES)
                                                .maximumSize(10_000)
                                                .build();
        String key = "name1";
        Object graph = manualCache.getIfPresent(key);

        graph = manualCache.get(key, k->createExpensiveGraph(k));

        manualCache.put(key, graph);

        manualCache.invalidate(key);

        ConcurrentMap<String, Object> map = manualCache.asMap();
        manualCache.invalidate(key);


        //同步加载
        LoadingCache<String, Object> loadingCache = Caffeine.newBuilder()
                                                    .maximumSize(10_000)
                                                    .expireAfterAccess(10, TimeUnit.MINUTES)
                                                    .build(key1->createExpensiveGraph(key1));

        graph = loadingCache.get(key);
        List<String> keys = new ArrayList<>();
        keys.add(key);
        Map<String, Object> graphs = loadingCache.getAll(keys);

        //异步加载
        AsyncLoadingCache<String, Object> asyncLoadingCache = Caffeine.newBuilder().maximumSize(10_000)
                                                                .expireAfterAccess(10, TimeUnit.MINUTES)
                                                                .buildAsync(key1 -> createExpensiveGraph(key1));
                                                                //Or: Build with a asynchronous computation that returns a future
                                                                //.buildAsync((key, executor)->createExpensiveGraph(key, executor))

        CompletableFuture<Object> graphFuture = asyncLoadingCache.get(key);

        CompletableFuture<Map<String, Object>> graphsFuture = asyncLoadingCache.getAll(keys);

        loadingCache = asyncLoadingCache.synchronous(); //异步转同步


//        LoadingCache<String, Graph> graphLoadingCache = Caffeine.newBuilder()
//                                                .expireAfter(new Expiry<String, Graph>() {
//                                                    @Override
//                                                    public long expireAfterCreate(@Nonnull String key, @Nonnull Graph value, long currentTime) {
//
//                                                        long seconds = 0L;
//                                                        return TimeUnit.SECONDS.toNanos(seconds);
//                                                    }
//
//                                                    @Override
//                                                    public long expireAfterUpdate(@Nonnull String key, @Nonnull Graph value, long currentTime, long currentDuration) {
//                                                        return currentDuration;
//                                                    }
//
//                                                    @Override
//                                                    public long expireAfterRead(@Nonnull String key, @Nonnull Graph value, long currentTime, long currentDuration) {
//                                                        return currentDuration;
//                                                    }
//                                                }).build(key2 -> createExpensiveGraph(key2));
    }
}
