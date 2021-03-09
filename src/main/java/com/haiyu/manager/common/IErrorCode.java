package com.haiyu.manager.common;
/**
 * 描述：封装API的错误码接口
 * @author ：fan
 * 创建时间：2020/4/16
 * 修改时间：
 **/
public interface IErrorCode {
    /**
     * 获取错误码
     * @param
     * @return long
     */
    long getCode();
    /**
     * 获取错误信息
     * @param
     * @return String
     */
    String getMsg();
}
