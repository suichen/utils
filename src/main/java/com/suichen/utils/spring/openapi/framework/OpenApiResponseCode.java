package com.suichen.utils.spring.openapi.framework;

/**
 * Created by Think on 2017/8/6.
 */
public enum OpenApiResponseCode {
    PARAMS_NOT_FIND(401, "param not find"),
    PARAMS_VALUE_IS_NULL(402, "params value is null"),
    HTTP_METHOD_NOT_SUPPORT(501, "http method not support"),
    PARAMS_ILLEGAL(403, "params is illegal");

    public int code;
    public String remark;

    OpenApiResponseCode(int code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    OpenApiResponseCode(String remark) {
        this.remark = remark;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
