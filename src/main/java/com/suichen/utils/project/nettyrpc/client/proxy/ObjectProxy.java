package com.suichen.utils.project.nettyrpc.client.proxy;

import com.suichen.utils.project.nettyrpc.client.ConnectMessage;
import com.suichen.utils.project.nettyrpc.client.RPCFuture;
import com.suichen.utils.project.nettyrpc.client.RpcClientHandler;
import com.suichen.utils.project.nettyrpc.protocol.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

public class ObjectProxy<T> implements InvocationHandler, IAsyncObjectProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectProxy.class);
    private Class<T> clazz;

    public ObjectProxy(Class<T> clazz) {
        this.clazz = clazz;
    }

    private Class<?> getClassType(Object obj) {
        Class<?> classType = obj.getClass();
        String typeName = classType.getName();

        switch (typeName) {
            case "java.lang.Integer":
                return Integer.TYPE;
            case "java.lang.Long":
                return Long.TYPE;
            case "java.lang.Float":
                return Float.TYPE;
            case "java.lang.Double":
                return Double.TYPE;
            case "java.lang.Character":
                return Character.TYPE;
            case "java.lang.Boolean":
                return Boolean.TYPE;
            case "java.lang.Short":
                return Short.TYPE;
            case "java.lang.Byte":
                return Byte.TYPE;
        }
        return classType;
    }
    private RpcRequest createRequest(String className, String methodName, Object[] args) {
        RpcRequest request = new RpcRequest();
        request.setRequestId(UUID.randomUUID().toString());
        request.setClassName(className);
        request.setMethodName(methodName);
        request.setParameters(args);

        Class[] parameterTypes = new Class[args.length];

        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = getClassType(args[i]);
        }

        request.setParameterTypes(parameterTypes);

        return request;
    }
    @Override
    public RPCFuture call(String funcName, Object... args) {
        RpcClientHandler handler = ConnectMessage.getInstance().chooseHandler();
        RpcRequest request = createRequest(this.clazz.getName(), funcName, args);
        RPCFuture rpcFuture = handler.sendRequest(request);
        return rpcFuture;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (Object.class == method.getDeclaringClass()) {
            String name = method.getName();
            if ("equals".equalsIgnoreCase(name)) {
                return proxy == args[0];
            }else if ("hashCode".equalsIgnoreCase(name)) {
                return System.identityHashCode(proxy);
            } else if ("toString".equalsIgnoreCase(name)) {
                return  proxy.getClass().getName()+"@"+Integer.toHexString(System.identityHashCode(proxy))
                        +", with InvocationHandler "+this;
            } else {
                throw new IllegalStateException(String.valueOf(method));
            }
        }

        RpcRequest request = new RpcRequest();
        request.setRequestId(UUID.randomUUID().toString());
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameterTypes(method.getParameterTypes());
        request.setParameters(args);

        LOGGER.debug(method.getDeclaringClass().getName());
        LOGGER.debug(method.getName());
        for (int i = 0; i < method.getParameterTypes().length; i++) {
            LOGGER.debug(method.getExceptionTypes()[i].getName());
        }

        for (int i = 0; i < args.length; i++) {
            LOGGER.debug(args[i].toString());
        }

        RpcClientHandler handler = ConnectMessage.getInstance().chooseHandler();
        RPCFuture rpcFuture = handler.sendRequest(request);
        return rpcFuture.get();
    }


}
