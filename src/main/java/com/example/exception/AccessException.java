package com.example.exception;

import com.alibaba.fastjson.JSON;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.utils.ServletUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权异常
 */
@Component
public class AccessException implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Result result = Result.error(Constants.CODE_600, "您的权限不足");
        String json = JSON.toJSONString(result);
        ServletUtils.renderString(httpServletResponse,json);
    }
}
