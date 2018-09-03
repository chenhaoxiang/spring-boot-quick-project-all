package com.huijava.common.enums;

import lombok.Getter;

/**
 * 响应码枚举，参考HTTP状态码的语义
 * @author chenhx
 * @version ResultCodeEnum.java, v 0.1 2018-09-03 下午 5:15
 */
@Getter
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(200,"SUCCESS"),
    /**
     * 失败
     */
    FAIL(400,"FAIL"),
    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED(401,"UNAUTHORIZED"),
    /**
     * 接口不存在
     */
    NOT_FOUND(404,"NOT_FOUND"),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500,"INTERNAL_SERVER_ERROR");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
