package com.suichen.utils.grpc;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;

public class HelloWorldClientFactory {//extends BasePooledObjectFactory<HelloWorldClient> {

//    @Override
//    public HelloWorldClient create() throws Exception {
//        return new HelloWorldClient("localhost", 50051);
//    }
//
//    @Override
//    public PooledObject<HelloWorldClient> wrap(HelloWorldClient helloWorldClient) {
//        return new DefaultPooledObject<>(helloWorldClient);
//    }
}
