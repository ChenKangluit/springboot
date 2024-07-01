package com.example.config;


import com.example.common.fileter.TokenFileter;
import com.example.exception.AccessException;
import com.example.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenFileter tokenFileter;

    @Autowired
    private AuthenticationException authenticationException;

    @Autowired
    private AccessException accessException;

    //密码加密编码
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }



    public void passwordencode(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("test"));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .csrf().disable()
                //禁用session会话
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()

                .authorizeRequests()
                .antMatchers("/user/login").anonymous()
                .antMatchers("/user/logout").permitAll()
                .antMatchers("/user/reLogin").permitAll()
                .antMatchers("/admin/acl/index/login").permitAll()
                .antMatchers("/admin/acl/index/logout").permitAll()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/**/export/").permitAll()
                .antMatchers("/**/import").permitAll()
                .antMatchers("/**/file/**").permitAll()
                //其它请求，需要认证
                .anyRequest().authenticated();

        //添加过滤器
        http
                .addFilterBefore(tokenFileter, UsernamePasswordAuthenticationFilter.class);

        //自定义异常
        http
                .exceptionHandling()
                //认证异常
                .authenticationEntryPoint(authenticationException)
                //授权异常
                .accessDeniedHandler(accessException);


    }
}
