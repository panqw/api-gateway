package com.fly.gateway.datasource;

public interface Connection {

    Object execute(String method, String[] parameterTypes, String[] parameterNames, Object[] args);

}
