package com.util.mongodb.common.api.code;

/**
 * IErrorCode
 *
 * @author ngchunho
 * @version 1.0.0
 * @description REST API 错误码接口
 * @date 2021/8/5 18:15
 */
public interface IErrorCode {

    /**
     * 错误编码 -1、失败 0、成功
     */
    long getCode();

    /**
     * 错误描述
     */
    String getMsg();
}
