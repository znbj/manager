package com.haiyu.manager.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述：通用返回对象
 *
 * @author fan
 * 创建时间：2020/4/16
 * 修改时间：
 **/
@Data
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResult<T> {

    private Long code;

    private String msg;

    private T data;

    private Long count;

    private int flag;

    protected CommonResult() {
    }

    protected CommonResult(long code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    protected CommonResult(long code, T data, String msg, int flag) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.flag = flag;
    }

    protected CommonResult(long code, String msg, T data, Long count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }


    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param msg  提示信息
     */
    public static <T> CommonResult<T> success(T data, String msg) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param msg  提示信息
     * @param flag 标示
     */
    public static <T> CommonResult<T> success(T data, String msg, int flag) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), data, msg, flag);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param msg  提示信息
     */
    public static <T> CommonResult<T> success(T data, String msg, Long count) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), msg, data, count);
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> ok() {
        return new CommonResult<T>(ResultCode.OK.getCode(), ResultCode.OK.getMsg(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> ok(T data) {
        return new CommonResult<T>(ResultCode.OK.getCode(), ResultCode.OK.getMsg(), data);
    }

    /**
     * 成功返回结果
     *
     * @param msg 消息
     */
    public static <T> CommonResult<T> ok(String msg) {
        return new CommonResult<T>(ResultCode.OK.getCode(), msg, null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> ok(T data, Long count) {
        return new CommonResult<T>(ResultCode.OK.getCode(), ResultCode.OK.getMsg(), data, count);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param msg  提示信息
     */
    public static <T> CommonResult<T> ok(T data, String msg) {
        return new CommonResult<T>(ResultCode.OK.getCode(), msg, data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param msg  提示信息
     */
    public static <T> CommonResult<T> ok(T data, String msg, Long count) {
        return new CommonResult<T>(ResultCode.OK.getCode(), msg, data, count);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMsg(), null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param msg       错误信息
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode, String msg) {
        log.error(msg);
        return new CommonResult<T>(errorCode.getCode(), msg, null);
    }

    /**
     * 失败返回结果
     *
     * @param msg 提示信息
     */
    public static <T> CommonResult<T> failed(String msg) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), msg, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param msg 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String msg) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), msg, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMsg(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMsg(), data);
    }

//    public long getCode() {
//        return code;
//    }
//
//    public void setCode(long code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//
//    public Long getCount() {
//        return count;
//    }
//
//    public void setCount(Long count) {
//        this.count = count;
//    }
//
//    public void setCode(Long code) {
//        this.code = code;
//    }
//
//    public int getFlag() {
//        return flag;
//    }
//
//    public void setFlag(int flag) {
//        this.flag = flag;
//    }
}
