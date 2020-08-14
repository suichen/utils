package com.suichen.utils.guava.collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class MapTest {
    private Multimap<String, String> getMultiMap() {
        Multimap<String, String> multimap = ArrayListMultimap.create();

        multimap.put("lower", "a");
        multimap.put("lower", "b");
        multimap.put("lower", "c");
        multimap.put("lower", "d");
        multimap.put("lower", "e");

        multimap.put("upper", "A");
        multimap.put("upper", "B");
        multimap.put("upper", "C");
        multimap.put("upper", "D");
        return multimap;
    }
    public static void main(String[] args) {
        MapTest mapTest = new MapTest();
        Multimap<String, String> multimap = mapTest.getMultiMap();


    }
}
