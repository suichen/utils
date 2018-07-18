package com.suichen.utils.cache;

import com.suichen.utils.cache.config.CacheConfig;
import com.suichen.utils.cache.model.CacheKey;
import com.suichen.utils.cache.model.CacheWrapper;


public interface Cache {
    /**
     * 设置缓存
     * @param key   缓存的key，不能为空
     * @param value 缓存的value，不能为空
     */
    void set(CacheKey key, CacheWrapper value);

    /**
     * 获取缓存的值
     * @param key   缓存的key,不能为空
     * @return  缓存的对象
     */
    CacheWrapper get(CacheKey key);

    /**
     * 删除的缓存
     * @param key   缓存的key
     * @return  删除的数量
     */
    long delete(CacheKey key);

    void clear();

    void shutdown();

    CacheConfig getConfig();

    void setMutex(CacheKey key);
}
