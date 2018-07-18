package com.suichen.utils.cache.model;

import org.springframework.util.StringUtils;

public class CacheKey extends BaseModel{
    private static final long serialVersionUID = 985800908443850484L;

    private String nameSpace;
    private String key;

    public CacheKey(String key) {
        this.key = key;
    }

    public CacheKey(String nameSpace, String key) {
        this.nameSpace = nameSpace;
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(nameSpace)) {
            sb.append(nameSpace).append(".");
        }
        sb.append(key);
        this.key = sb.toString();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
