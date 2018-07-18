package com.suichen.utils.proxy;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class MyInvocationHandler implements InvocationHandler, org.springframework.cglib.proxy.InvocationHandler {
    private ServerInfo serverInfo;

    private BeanFactory beanFactory;
    private RequestHandler handler;

    public MyInvocationHandler(ServerInfo serverInfo, BeanFactory beanFactory) {
        this.serverInfo = serverInfo;
        this.beanFactory = beanFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequestInfo requestInfo = extractRequestInfo(method, args);
        return getRequestHandler().handler(serverInfo, requestInfo);
    }

    private synchronized RequestHandler getRequestHandler() {
        if(this.handler == null) {
            this.handler = this.beanFactory.getBean(RequestHandler.class);
        }
        return this.handler;
    }
    private RequestInfo extractRequestInfo(Method method, Object[] args) {
        RequestInfo requestInfo = new RequestInfo();
        GET get = method.getAnnotation(GET.class);
        if (get == null) {
            throw new RuntimeException("Proxy class does not have GET annotation!");
        }

        String url = get.value();
        if(StringUtils.isEmpty(url)) {
            url = "/" + method.getName();
        }
        requestInfo.setUrl(url);
        requestInfo.setReturnType(method.getReturnType());
        requestInfo.setParamsMap(extractParamsValue(method, args));

        return requestInfo;
    }

    private Map<String, Object> extractParamsValue(Method method, Object[] args) {
        Map<String, Object> paramsMap = new HashMap<>();

        int cnt = method.getParameterCount();
        if (cnt <= 0) return null;
        cnt = 0;

        for (Parameter parameter:method.getParameters()) {

            Params params = parameter.getDeclaredAnnotation(Params.class);
            if (params == null) {
                throw new RuntimeException("Annotation Params is null");
            }

            String paramName = params.value();
            if (StringUtils.isEmpty(paramName)) {
                paramName = this.parameterNameDiscoverer.getParameterNames(method)[cnt];
            }

            paramsMap.put(paramName, args[cnt]);
            cnt += 1;
        }

        return paramsMap;
    }

    private ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
}
