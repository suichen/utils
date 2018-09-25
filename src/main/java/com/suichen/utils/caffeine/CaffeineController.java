package com.suichen.utils.caffeine;

import com.github.benmanes.caffeine.cache.*;
import com.google.common.testing.FakeTicker;
import com.google.common.util.concurrent.FakeTimeLimiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@RestController
public class CaffeineController {
    Cache<String, Object> manualCache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(10_000)
            .build();

    LoadingCache<String, Object> loadingCache = Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(key -> createExpensiveGraph(key));

    AsyncLoadingCache<String, Object> asyncLoadingCache = Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            // Either: Build with a synchronous computation that is wrapped as asynchronous
            .buildAsync(key -> createExpensiveGraph(key));
    // Or: Build with a asynchronous computation that returns a future
    // .buildAsync((key, executor) -> createExpensiveGraphAsync(key, executor));

    private CompletableFuture<Object> createExpensiveGraphAsync(String key, Executor executor) {
        CompletableFuture<Object> objectCompletableFuture = new CompletableFuture<>();
        return objectCompletableFuture;
    }

    private Object createExpensiveGraph(String key) {
        System.out.println("缓存不存在或过期，调用了createExpensiveGraph方法获取缓存key的值");
        if (key.equals("name")) {
            throw new RuntimeException("调用了该方法获取缓存key的值的时候出现异常");
        }
        return "person";
    }

    @RequestMapping("/testSizeBased")
    public Object testSizeBased() {
        LoadingCache<String, Object> cache = Caffeine.newBuilder()
                .maximumSize(1)
                .build(k -> createExpensiveGraph(k));

        cache.get("A");
        System.out.println(cache.estimatedSize());
        cache.get("B");

        cache.cleanUp();
        System.out.println(cache.estimatedSize());

        return "";
    }

    @RequestMapping("/testTimeBased")
    public Object testTimeBased() {
        String key = "name1";
        // 用户测试，一个时间源，返回一个时间值，表示从某个固定但任意时间点开始经过的纳秒数。
        FakeTicker ticker = new FakeTicker();

        // 基于固定的到期策略进行退出
        // expireAfterAccess
        LoadingCache<String, Object> cache1 = Caffeine.newBuilder()
                .ticker(ticker::read)
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .build(k -> createExpensiveGraph(k));

        System.out.println("expireAfterAccess：第一次获取缓存");
        cache1.get(key);

        System.out.println("expireAfterAccess：等待4.9S后，第二次次获取缓存");
        // 直接指定时钟
        ticker.advance(4900, TimeUnit.MILLISECONDS);
        cache1.get(key);

        System.out.println("expireAfterAccess：等待0.101S后，第三次次获取缓存");
        ticker.advance(101, TimeUnit.MILLISECONDS);
        cache1.get(key);

        // expireAfterWrite
        LoadingCache<String, Object> cache2 = Caffeine.newBuilder()
                .ticker(ticker::read)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .build(k -> createExpensiveGraph(k));

        System.out.println("expireAfterWrite：第一次获取缓存");
        cache2.get(key);

        System.out.println("expireAfterWrite：等待4.9S后，第二次次获取缓存");
        ticker.advance(4900, TimeUnit.MILLISECONDS);
        cache2.get(key);

        System.out.println("expireAfterWrite：等待0.101S后，第三次次获取缓存");
        ticker.advance(101, TimeUnit.MILLISECONDS);
        cache2.get(key);

        // Evict based on a varying expiration policy
        // 基于不同的到期策略进行退出
        LoadingCache<String, Object> cache3 = Caffeine.newBuilder()
                .ticker(ticker::read)
                .expireAfter(new Expiry<String, Object>() {

                    @Override
                    public long expireAfterCreate(String key, Object value, long currentTime) {
                        // Use wall clock time, rather than nanotime, if from an external resource
                        return TimeUnit.SECONDS.toNanos(5);
                    }

                    @Override
                    public long expireAfterUpdate(String key, Object graph,
                                                  long currentTime, long currentDuration) {

                        System.out.println("调用了 expireAfterUpdate：" + TimeUnit.NANOSECONDS.toMillis(currentDuration));
                        return currentDuration;
                    }

                    @Override
                    public long expireAfterRead(String key, Object graph,
                                                long currentTime, long currentDuration) {

                        System.out.println("调用了 expireAfterRead：" + TimeUnit.NANOSECONDS.toMillis(currentDuration));
                        return currentDuration;
                    }
                })
                .build(k -> createExpensiveGraph(k));

        System.out.println("expireAfter：第一次获取缓存");
        cache3.get(key);

        System.out.println("expireAfter：等待4.9S后，第二次次获取缓存");
        ticker.advance(4900, TimeUnit.MILLISECONDS);
        cache3.get(key);

        System.out.println("expireAfter：等待0.101S后，第三次次获取缓存");
        ticker.advance(101, TimeUnit.MILLISECONDS);
        Object object = cache3.get(key);

        return object;
    }

    @RequestMapping("/testRemoval")
    public Object testRemoval() {
        String key = "name1";
        FakeTicker ticker = new FakeTicker();

        LoadingCache<String, Object> cache = Caffeine.newBuilder().removalListener(
                (String k, Object graph, RemovalCause cause) ->
                        System.out.printf("Key %s was removed (%s)%n", k, cause)

        ).ticker(ticker::read).expireAfterAccess(5, TimeUnit.SECONDS).build(k->createExpensiveGraph(k));


        System.out.println("第一次获取缓存");
        Object object = cache.get(key);


        System.out.println("等待6S后，第二次次获取缓存");
        ticker.advance(6000, TimeUnit.MILLISECONDS);
        cache.get(key);

        //System.out.println("手动删除缓存");
        //cache.invalidate(key);

        return object;
    }
}
