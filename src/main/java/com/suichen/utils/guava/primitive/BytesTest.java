package com.suichen.utils.guava.primitive;

import com.google.common.primitives.Bytes;

import java.util.List;

public class BytesTest {
    public static void main(String[] args) {
        byte[] byteArray = {1,2,3,4,5,6,7,8,9};
        List<Byte> objectByte = Bytes.asList(byteArray);
        System.out.println(objectByte.toString());

        byteArray = Bytes.toArray(objectByte);
        System.out.println("[");
        for (int i = 0; i < byteArray.length; i++) {

        }
    }
}
