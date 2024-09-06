package com.fly.gateway.proxy;


import com.fly.gateway.executor.SessionResult;

import java.util.Map;

/**
 * @author 小傅哥，微信：fustack
 * @description 统一泛化调用接口
 * @github github.com/fuzhengwei
 * @copyright 公众号：bugstack虫洞栈 | 博客：bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface IGenericReference {

    SessionResult $invoke(Map<String, Object> params);

}