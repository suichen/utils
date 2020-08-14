package com.suichen.utils.netty.rpcdemo;


import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RpcNioMultServerTask implements Runnable{

    private byte[] bytes;
    private SocketChannel channel;

    public RpcNioMultServerTask(byte[] bytes, SocketChannel channel) {
        this.bytes = bytes;
        this.channel = channel;
    }

    public void requestHandle(RequstMultObject requstObject, SocketChannel channel) {
        long requestId = requstObject.getRequestId();
        Object obj = BeanContainer.getBean(requstObject.getCalzz());
        String methodName = requstObject.getMethodName();
        Class<?>[] parameterTypes = requstObject.getParamTypes();
        Object[] arguments = requstObject.getArgs();

        try {
            Method method = obj.getClass().getMethod(methodName, parameterTypes);
            String result = (String) method.invoke(obj, arguments);
            byte[] bytes = SerializeUtil.serialize(result);
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length+12);

            buffer.putLong(requestId);
            buffer.putInt(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            channel.write(buffer);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        if (bytes!=null && bytes.length>0 && channel!=null) {
            RequstMultObject requstMultObject = (RequstMultObject) SerializeUtil.unSerialize(bytes);
            requestHandle(requstMultObject, channel);
        }
    }

    public SocketChannel getChannel() {
        return channel;
    }

    public void setChannel(SocketChannel channel) {
        this.channel = channel;
    }
}
