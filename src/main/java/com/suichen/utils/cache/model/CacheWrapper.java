package com.suichen.utils.cache.model;

public class CacheWrapper extends BaseModel{
    private static final long serialVersionUID = -4627300244753708729L;

    private Object  obj;

    //本地缓存使用的字段 redis不会使用下面的字段
    /**过期时间,单位:秒，0表示不过期*/
    private int     expireTime = 0;
    /**创建时间*/
    private long    createTime = 0;
    /**最后一次访问时间*/
    private long    lastAccessTime = 0;

    public CacheWrapper(Object obj) {
        this.obj = obj;
    }

    public CacheWrapper(Object obj, int expireTime) {
        this.obj = obj;
        this.expireTime = expireTime;
        this.createTime = System.currentTimeMillis();
        this.lastAccessTime = System.currentTimeMillis();
    }

    public boolean isExpire() {
        if (expireTime > 0) {
            return this.createTime + 1000*this.expireTime < System.currentTimeMillis();
        }

        return false;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }
}
