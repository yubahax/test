package com.example.yubatest.Interceptor;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SaTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        if (requestURI.contains("js") || requestURI.contains("css") || requestURI.contains("jpg") || requestURI.contains("jpeg") || requestURI.contains("png") || requestURI.contains("html") ) {
            return true;
        }


        if ("/login".equals(requestURI) || "/register".equals(requestURI) || requestURI.contains("swagger") || requestURI.contains("v3")) {
            return true;
        }

        StpUtil.checkLogin();

        return true;
    }
}
