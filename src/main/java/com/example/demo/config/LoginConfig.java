package com.example.demo.config;

import com.example.demo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册登录拦截器
 *
 * @author 搬砖的码农
 * @date 2023/03/21
 * @email
 **/
@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //拦截所有请求
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/test","/login","/register","/fileImage","/payOrder");
    }
}
