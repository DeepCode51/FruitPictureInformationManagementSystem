package com.spring.config;

import com.spring.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class TokenConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext context;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TokenInterceptor拦截器
        //context.getBean(TokenInterceptor.class);
        InterceptorRegistration registration = registry.addInterceptor(context.getBean(TokenInterceptor.class));
        registration.addPathPatterns("/**"); //所有路径都被拦截
    }
}
