package com.fly.gateway.executor;

/**
 * @author 小傅哥，微信：fustack
 * @description 会话结果封装
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class SessionResult {

    private String code;
    private String info;
    private Object data;

    protected SessionResult(String code, String info, Object data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static SessionResult buildSuccess(Object data) {
        return new SessionResult("0000", "调用成功", data);
    }

    public static SessionResult buildError(Object data) {
        return new SessionResult("0001", "调用失败", data);
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public Object getData() {
        return data;
    }

}
