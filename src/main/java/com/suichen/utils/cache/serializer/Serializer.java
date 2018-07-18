package com.suichen.utils.cache.serializer;

public interface Serializer<T> {
    byte[] serialize(T t) throws Exception;
    T deserialize(byte[] bytes) throws Exception;
}
