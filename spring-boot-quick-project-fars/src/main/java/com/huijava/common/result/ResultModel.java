/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.common.result;

import com.huijava.common.enums.ResultCodeEnum;
import lombok.Data;

/**
 * 统一API响应结果封装
 * @author chenhx
 * @version ResultModelAAA.java, v 0.1 2018-09-03 下午 7:53
 */
@Data
public class ResultModel<T> {
    private Integer code;
    private String message;
    private T data;

    public ResultModel(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultModel(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultModel(ResultCodeEnum resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }


    public static ResultModel resultModel(Integer code,String message){
        return new ResultModel(code,message);
    }

    public static <T>ResultModel resultModel(Integer code,String message,T t){
        return new ResultModel(code,message,t);
    }

    /**
     * 处理成功
     * @param t
     * @param <T>
     * @return
     */
    public static <T>ResultModel success(T t){
        ResultModel resultModel = new ResultModel(ResultCodeEnum.SUCCESS);
        resultModel.setData(t);
        return resultModel;
    }
    public static ResultModel success(){
        return success(null);
    }

    /**
     * 业务处理失败
     * @param t
     * @param <T>
     * @return
     */
    public static <T>ResultModel fail(T t){
        ResultModel resultModel = new ResultModel(ResultCodeEnum.FAIL);
        resultModel.setData(t);
        return resultModel;
    }
    public static ResultModel fail(){
        return fail(null);
    }

    /**
     * 服务器内部错误
     * @param t
     * @param <T>
     * @return
     */
    public static <T>ResultModel error(T t){
        ResultModel resultModel = new ResultModel(ResultCodeEnum.INTERNAL_SERVER_ERROR);
        resultModel.setData(t);
        return resultModel;
    }
    public static ResultModel error(){
        return error(null);
    }

    /**
     * 认证不通过
     * @param t
     * @param <T>
     * @return
     */
    public static <T>ResultModel unAuthorized(T t){
        ResultModel resultModel = new ResultModel(ResultCodeEnum.UNAUTHORIZED);
        resultModel.setData(t);
        return resultModel;
    }
    public static ResultModel unAuthorized(){
        return unAuthorized(null);
    }

}
