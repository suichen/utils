package com.suichen.utils.proxy;

public interface RequestHandler {
    Object handler(ServerInfo serverInfo, RequestInfo requestInfo);
}
