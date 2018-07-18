package com.suichen.utils.cache.config;

import java.io.File;

public class CacheConfig {
    /**缓存命名空间，可避免多个应用使用缓存时造成key冲突*/
    private String      nameSpace;
    /**缓存条数,用户本地缓存*/
    private int         maxCacheNums            = 3000;
    /**当缓存达到maxCacheNums时采取的缓存丢弃策略, 默认FIFO*/
    private String      discardPolicy           = "fifo";
    /**缓存存储路径*/
    private String      persistecePath          = File.separatorChar+"tmp"+ File.separatorChar+"cache";
    /**是否持久化*/
    private boolean     isPersist               = true;
    /** 每隔多长时间持久化缓存,单位:秒 */
    private int     timeBetweenPersist = 5;

    public String getNameSpace() {
        return nameSpace;
    }

    public CacheConfig setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
        return this;
    }

    public int getMaxCacheNums() {
        return maxCacheNums;
    }

    public CacheConfig setMaxCacheNums(int maxCacheNums) {
        this.maxCacheNums = maxCacheNums;
        return this;
    }

    public String getDiscardPolicy() {
        return discardPolicy;
    }

    public CacheConfig setDiscardPolicy(String discardPolicy) {
        this.discardPolicy = discardPolicy;
        return this;
    }

    public String getPersistecePath() {
        return persistecePath;
    }

    public CacheConfig setPersistecePath(String persistecePath) {
        this.persistecePath = persistecePath;
        return this;
    }

    public boolean isPersist() {
        return isPersist;
    }

    public CacheConfig setPersist(boolean persist) {
        isPersist = persist;
        return this;
    }

    public int getTimeBetweenPersist() {
        return timeBetweenPersist;
    }

    public CacheConfig setTimeBetweenPersist(int timeBetweenPersist) {
        this.timeBetweenPersist = timeBetweenPersist;
        return this;
    }
}
