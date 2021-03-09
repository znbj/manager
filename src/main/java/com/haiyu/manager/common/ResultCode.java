package com.haiyu.manager.common;

/**
 * 描述：枚举-常用API操作码
 * 作者：fan
 * 创建时间：2020/4/16
 * 修改时间：
 *
 * @author fan*/
public enum ResultCode implements IErrorCode {
    /**
     * 成功
     * */
    SUCCESS(200, "请求成功"),

    /**
     * 成功 兼容layui
     * */
    OK(0, "请求成功"),

    /**
     * 失败
     * */
    FAILED(500, "操作失败"),
    /**
     * 验证失败
     * */
    VALIDATE_FAILED(404, "参数检验失败"),
    /**
     * 未登录
     * */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /**
     * 没有相关权限
     * */
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String msg;

    private ResultCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    @Override
    public long getCode() {
        return code;
    }
    @Override
    public String getMsg() {
        return msg;
    }
}
