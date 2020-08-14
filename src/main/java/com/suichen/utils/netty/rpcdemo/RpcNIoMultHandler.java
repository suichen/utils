package com.suichen.utils.netty.rpcdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RpcNIoMultHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        Long responseId = RpcContainer.getReuestId();
        RequstMultObject requstMultObject = new RequstMultObject(
                method.getDeclaringClass(),
                method.getName(),
                method.getParameterTypes(),
                args
        );
        requstMultObject.setRequestId(responseId);

        //封装设置rpcResponseFuture,主要用于获取返回值
        RpcResponseFuture rpcResponseFuture = new RpcResponseFuture(responseId);
        RpcContainer.addRequestFuture(rpcResponseFuture);

        byte[] requestBytes = SerializeUtil.serialize(requstMultObject);
        RpcNioMultClient rpcNioMultClient = RpcNioMultClient.getInstance();
        rpcNioMultClient.sendMsg2Server(requestBytes);

        //从ResponseContainer获取返回值
        byte[] responseBytes = rpcResponseFuture.get();
        if (responseBytes!=null) {
            RpcContainer.removeResponseAndFuture(responseId);
        }

        //反序列化获得结果
        Object result = SerializeUtil.unSerialize(responseBytes);
        System.out.println("请求id: "+responseId+" 结果: "+result);
        return result;

    }
}
