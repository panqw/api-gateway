package com.fly.gateway.executor;

import com.fly.gateway.mapping.HttpStatement;

import java.util.Map;

public interface Executor {

    SessionResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception;
}
