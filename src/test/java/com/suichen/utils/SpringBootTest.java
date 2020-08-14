package com.suichen.utils;

import org.junit.Test;

import java.util.*;

public class SpringBootTest {
    @Test
    public void testEnvironment() {
        Map<String, Object> map1 = new HashMap<>();
        List<String> value1 = new ArrayList<>();
        value1.add("bar");
        value1.add("spam");
        map1.put("foo", value1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("foo.bar", "spam");

        Map<String, Object> map3 = new HashMap<>();
        Map<String, Object> value3 = new HashMap<>();
        value3.put("bar", "spam");
        value3.put("rab", "maps");
        map3.put("foo", value3);

        Map<String, Object> result = flatten(map3);

        System.out.println();
    }

    private Map<String, Object> flatten(Map<String, Object> map) {
        Map<String, Object> result = new LinkedHashMap<>();
        flatten(null, result, map);
        return result;
    }

    private void flatten(String prefix, Map<String, Object> result, Map<String, Object> map) {
        String namePrefix = (prefix!=null) ? prefix+".":"";
        map.forEach((key,value)->extract(namePrefix+key, result, value));
    }

    private void extract(String name, Map<String, Object> result, Object value) {
        if (value instanceof Map) {
            flatten(name, result, (Map<String, Object>) value);
        } else if (value instanceof Collection) {
            int index = 0;
            for (Object object:(Collection<Object>)value) {
                extract(name+"["+index+"]", result, object);
                index++;
            }
        } else {
            result.put(name, value);
        }
    }
}
