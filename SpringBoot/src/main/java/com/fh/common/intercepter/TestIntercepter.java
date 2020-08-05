package com.fh.common.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        String originalURL = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", originalURL);
        String method = request.getMethod();
        if(method.equalsIgnoreCase("options")){
            response.setHeader("Access-Control-Allow-Headers", "token");
            return false;
        }
        return true;
    }
}
