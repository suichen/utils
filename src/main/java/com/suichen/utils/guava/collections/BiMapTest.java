package com.suichen.utils.guava.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class BiMapTest {
    public static void main(String[] args) {
        BiMap<Integer, String> empIDNameMap = HashBiMap.create();
        empIDNameMap.put(new Integer(101), "Mahesh");
        empIDNameMap.put(new Integer(102), "Sohah");
        empIDNameMap.put(new Integer(103), "Ramesh");

        System.out.println(empIDNameMap.inverse().get("Mahesh"));
    }
}
