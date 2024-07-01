package com.example.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUtils {

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            // 允许跨域
            response.setHeader("Access-Control-Allow-Origin", "*");
            // 允许自定义请求头token(允许head跨域)
            response.setHeader("Access-Control-Allow-Headers", "Authorization, authorization,token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}