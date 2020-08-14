package com.suichen.utils.spring.openapi.framework;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * Created by Think on 2017/7/29.
 */
@Service
public class OpenApiService {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OpenApiResult call(@PathVariable(value = "OpenApiType") String OpenApiType,
                                Map<String, Object> requestParams,
                              Map<String, Object> bodyParams,
                              Map<String, Object> headerParams,
                              String method) {
        Object[] args;

        try {
            OpenApiMethodInfo info = getMethodInfo(OpenApiType);
            checkHttpMethod(method);

            args = handleParamsNameAndValue(info.getMethod(), requestParams, bodyParams, headerParams);

        }catch (OpenApiException e) {

        }catch (Throwable e) {

        }

        return null;
    }

    private OpenApiMethodInfo getMethodInfo(String openApiType) {
        return OpenApiRegsister.getOpenApiMethodInfo(openApiType);
    }

    private void checkHttpMethod(String method) {
        for (HttpMethod httpMethod: HttpMethod.values()) {
            if (httpMethod.name().equals(method)) {
                return;
            }
        }

        throw new OpenApiException(method+" method is not support");
    }

    private Object[] handleParamsNameAndValue(Method method,
                                            Map<String, Object> requestParams,
                                            Map<String, Object> bodyParams,
                                            Map<String, Object> headerParams) {
        int cnt = method.getParameterCount();

        if (cnt == 0) return null;

        Object[] args = new Object[cnt];
        cnt = 0;

        for (Parameter parameter:method.getParameters()) {
            handleEachParam(method, requestParams, bodyParams, headerParams,
                    args, parameter, cnt);
            cnt += 1;
        }

        return args;
    }

    private boolean isParamRequired(OpenApiParams openApiParams) {
        return (openApiParams==null || openApiParams.required());
    }

    private void checkIsRequired(boolean required, Object value) {
        if (required) {
            if (null == value || "".equals(value)) {
                throw new OpenApiException(OpenApiResponseCode.PARAMS_VALUE_IS_NULL.getCode(),
                            OpenApiResponseCode.PARAMS_VALUE_IS_NULL.getRemark());
            }
        }
    }

    private String getParamName(Method method, OpenApiParams openApiParams, int index) {
        return (openApiParams==null || StringUtils.isEmpty(openApiParams.value()))
                ? getMethodParams(method, index) : openApiParams.value();
    }

    private String getMethodParams(Method method, int index) {
        return this.parameterNameDiscoverer.getParameterNames(method)[index];
    }

    private Object getParamValue(OpenApiParams openApiParams, Map<String, Object> requestParams,
                                 Map<String, Object> bodyParams,
                                 Map<String, Object> headerParams,
                                 String paramName) {
        switch (openApiParams.origin()) {
            case request:
                return requestParams.get(paramName);
            case body:
                return bodyParams.get(paramName);
            case header:
                return headerParams.get(paramName);
            default:
                return null;
        }
    }

    private void handleEachParam(Method method, Map<String, Object> requestParams,
                                   Map<String, Object> bodyRequest,
                                   Map<String, Object> headerParams,
                                   Object[] args, Parameter parameter, int index) {

        OpenApiParams openApiParams = parameter.getAnnotation(OpenApiParams.class);

        String paramName = getParamName(method, openApiParams, index);

        Object paramValue = getParamValue(openApiParams, requestParams,
                                bodyRequest, headerParams, paramName);

        checkIsRequired(isParamRequired(openApiParams), paramValue);

        args[index] = convertParamValue(paramValue, method, paramName, index);

    }

    private Object convertParamValue(Object oldValue, Method method,
                                     String paramName, int index) {

        try {
            return typeConverter.convertIfNecessary(oldValue,
                    method.getParameterTypes()[index], new MethodParameter(method, index));
        }catch (TypeMismatchException e) {
            e.printStackTrace();

            throw new OpenApiException(OpenApiResponseCode.PARAMS_ILLEGAL);
        }
    }

    private ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    private SimpleTypeConverter typeConverter = new SimpleTypeConverter();
}
