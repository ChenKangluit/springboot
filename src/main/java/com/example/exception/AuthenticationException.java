package com.example.exception;

import com.alibaba.fastjson.JSON;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.utils.ServletUtils;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证异常
 */
@Component
public class AuthenticationException implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.core.AuthenticationException e) throws IOException, ServletException {
        Result result = Result.error(Constants.CODE_600, "错误");
        String json = JSON.toJSONString(result);
        ServletUtils.renderString(httpServletResponse,json);
    }
}
