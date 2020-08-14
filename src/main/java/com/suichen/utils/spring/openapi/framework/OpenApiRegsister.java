package com.suichen.utils.spring.openapi.framework;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Think on 2017/8/5.
 */
@Component
public class OpenApiRegsister {
    private static Map<OpenApiType, OpenApiMethodInfo> maps = new ConcurrentHashMap<>();


    public static void register(OpenApiType name, OpenApiMethodInfo info) {
        maps.putIfAbsent(name, info);
    }

    public static OpenApiMethodInfo getOpenApiMethodInfo(String name) {
        return maps.get(name);
    }
}
