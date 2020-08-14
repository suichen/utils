package com.suichen.utils.netty.rpcdemo;

import java.io.*;

public class SerializeUtil {
    public static Object unSerialize(byte[] bytes) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();
        }catch (IOException e) {
            System.out.println("反序列化失败!");
            e.printStackTrace();
            return null;
        }catch (ClassNotFoundException ee) {
            System.out.println("反序列化失败!");
            ee.printStackTrace();
            return null;
        }
    }
    public static byte[] serialize(Object obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            byte[] bytes = bos.toByteArray();
            return bytes;
        }catch (IOException e) {
            System.out.println("序列化对象出错！");
            e.printStackTrace();
            return null;
        }
    }




































































































}
