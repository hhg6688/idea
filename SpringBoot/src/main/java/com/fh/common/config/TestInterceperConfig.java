package com.fh.common.config;

import com.fh.common.intercepter.LoginInterceptor;
import com.fh.common.intercepter.TestIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//拦截器的配置   拦截那些东西  不拦截那些东西
@Configuration //声明是配置文件类
public class TestInterceperConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new TestIntercepter());
        registration.addPathPatterns("/**");//所有路径都被拦截

        InterceptorRegistration registration1= registry.addInterceptor(new LoginInterceptor());
        registration1.addPathPatterns("/CartController/**");
        registration1.addPathPatterns("/AddressController/**");
        registration1.addPathPatterns("/OrderController/**");
    }
}
