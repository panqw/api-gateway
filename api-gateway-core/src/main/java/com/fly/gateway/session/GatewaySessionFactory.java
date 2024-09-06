package com.fly.gateway.session;

/**
 * @author 小傅哥，微信：fustack
 * @description 网关会话工厂接口
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface GatewaySessionFactory {

    GatewaySession openSession(String uri);

}