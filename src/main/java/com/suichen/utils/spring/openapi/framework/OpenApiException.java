package com.suichen.utils.spring.openapi.framework;

/**
 * Created by Think on 2017/8/6.
 */
public class OpenApiException extends RuntimeException{
    private int code;
    private String remark;

    public OpenApiException(OpenApiResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.remark = responseCode.getRemark();
    }

    public OpenApiException(int code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public OpenApiException(String remark) {
        this.remark = remark;
    }
}
