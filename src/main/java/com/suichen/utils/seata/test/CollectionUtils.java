package com.suichen.utils.seata.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionUtils {
    private static final String KV_SPLIT = "=";
    private static final String PAIR_SPLIT = "&";

    public static Map<String, String> decodeMap(String data) {
        if (data == null) {
            return null;
        }

        Map<String, String> map = new ConcurrentHashMap<>();

        String[] kvPairs = data.split(PAIR_SPLIT);

        if (kvPairs.length == 0) {
            return map;
        }

        for (String kvPair:kvPairs) {
            String[] kvs = kvPair.split("=");
            map.put(kvs[0], kvs[1]);
        }

        return map;
    }
}
