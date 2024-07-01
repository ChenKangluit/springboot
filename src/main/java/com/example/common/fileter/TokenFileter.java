package com.example.common.fileter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.entity.LoginUser;
import com.example.exception.ServiceException;
import com.example.utils.RedisUtil;
import com.example.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFileter extends OncePerRequestFilter {
    @Autowired
    private RedisUtil redisUtil;

    @Value("${token.sign}")
    private String signkey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("token");
        //String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiZXhwIjoxNjg3OTc0NTg2fQ.xX8cuebnsczLL0okW1digiyICmWiF39H-tCkPc3xsro";
        String url= request.getServletPath();
        System.out.println("请求的地址为：："+url);
        if(url.indexOf("/user/logout")>-1 || url.indexOf("/user/reLogin")>-1){
            token=null;
        }

        if(url.indexOf("/user/userInfo")>-1){
            System.out.println("token::"+token);
        }

        //TODO 判断 token 是否存在
        if (StrUtil.isBlank(token)){
            filterChain.doFilter(request,response);
            return;
        }


        //TODO 获取 token中的id  进行验证 是否存在
        String userId;
        try {
            userId =  redisUtil.get(token).toString();
        } catch (JWTDecodeException e) {
            throw new ServiceException(Constants.CODE_401, "登录信息已过期");
        }

        //TODO 验证成功后 去 redis 检查一下 是否有着个用户 id
        LoginUser loginUser = (LoginUser) redisUtil.get(userId);


        if (loginUser == null) {
            throw new ServiceException(Constants.CODE_401, "未登录");
        }
        //TODO 验证token 签名验证
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(signkey)).build();
        try {
            jwtVerifier.verify(token); //加签验证
        } catch (JWTVerificationException e) {
            throw  new ServiceException(Constants.CODE_401, "验证失败,请重新登陆");
        }

        //TODO 存入 SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        filterChain.doFilter(request,response);
    }
}
