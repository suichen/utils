package com.suichen.utils.cache.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HessianSerializer<T> implements Serializer<T>{
    private static final Logger logger            = LoggerFactory.getLogger(HessianSerializer.class);

    //private final SerializerFactory
    @Override
    public byte[] serialize(T t) throws Exception {
        return new byte[0];
    }

    @Override
    public T deserialize(byte[] bytes) throws Exception {
        return null;
    }
}
