/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.common.interceptors;

import com.alibaba.fastjson.JSON;
import com.huijava.common.base.BaseController;
import com.huijava.common.result.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 签名认证拦截器
 * @author chenhx
 * @version SignatureAuthenticationInterceptors.java, v 0.1 2018-09-03 下午 7:49
 */
@Slf4j
public class SignatureAuthenticationInterceptors extends BaseController implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //验证签名
        boolean pass = validateSign(request);
        if (pass) {
            return true;
        } else {
            log.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
                    request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
            responseResult(response, ResultModel.unAuthorized());
            return false;
        }
    }
}
