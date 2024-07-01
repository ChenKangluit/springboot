package com.example.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.entity.LoginUser;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class TokenUtils {

    @Value(value = "${token.sign}")
    private String sign;

    private static String SIGNKEY;


    @PostConstruct
    public void init() {
        SIGNKEY = sign;
    }

    /***
     * 生成 token
     * @param userId
     * @return
     */
    public static  String  getToken(String userId){
        return JWT.create()
                .withAudience(userId) //userid 作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),2)) //2小时过期时间
                .sign(Algorithm.HMAC256(SIGNKEY)); //  密钥
    }

    /**
     * 获取当前登录用户
     *
     * @return 用户登录信息
     */
    public static LoginUser getLoginUser() {
        return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return null;
    }

}
