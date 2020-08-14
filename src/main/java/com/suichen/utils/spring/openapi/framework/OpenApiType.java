package com.suichen.utils.spring.openapi.framework;

/**
 * Created by Think on 2017/7/29.
 */
public enum OpenApiType {
    helloOpenApi("hello open api");

    String remark;

    OpenApiType(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOpenApiTypeName(OpenApiType type) {
        for (OpenApiType temp:OpenApiType.values()) {
            if (temp.name().equals(type.name())) {
                return type.name();
            }
        }

        return null;
    }
}
