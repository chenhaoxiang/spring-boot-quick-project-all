/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.common.handler;

import com.huijava.common.base.BaseController;
import com.huijava.common.enums.ResultCodeEnum;
import com.huijava.common.exception.ServiceException;
import com.huijava.common.result.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常捕获类
 * @author chenhx
 * @version ExceptionHandler.java, v 0.1 2018-09-03 下午 7:41
 */
@Slf4j
public class ExceptionHandler extends BaseController implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ResultModel result;
        //业务失败的异常，如“账号或密码错误”
        if (ex instanceof ServiceException) {
            log.error(ex.getMessage());
            result = ResultModel.resultModel(ResultCodeEnum.FAIL.getCode(),ex.getMessage());
        } else if (ex instanceof NoHandlerFoundException) {
            result = ResultModel.resultModel(ResultCodeEnum.NOT_FOUND.getCode(),"接口 [" + request.getRequestURI() + "] 不存在");
        }else {
            result = ResultModel.resultModel(ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode(),"接口 [" + request.getRequestURI() + "] 内部错误，请联系客服");
            String message;
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常信息：%s",
                        request.getRequestURI(),
                        handlerMethod.getBean().getClass().getName(),
                        handlerMethod.getMethod().getName(),
                        ex.getMessage());
            } else {
                message = ex.getMessage();
            }
            log.error(message, ex);
        }
        responseResult(response, result);
        return new ModelAndView();
    }
}
