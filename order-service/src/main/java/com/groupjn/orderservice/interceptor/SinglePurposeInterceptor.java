package com.groupjn.orderservice.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SinglePurposeInterceptor implements HandlerInterceptor {

    private final Logger LOG = LoggerFactory.getLogger(SinglePurposeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("In PreHandle method of SinglePurposeInterceptor request uri{"+ request.getRequestURI().toString()+"}");
        LOG.info("In PreHandle method of SinglePurposeInterceptor request uri{"+ request.toString()+"}");
        LOG.info("In PreHandle method of SinglePurposeInterceptor response {"+ response.toString()+"}");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        LOG.info("In PostHandle method of SinglePurposeInterceptor request uri{"+ request.getRequestURI().toString()+"}");
        LOG.info("In PostHandle method of SinglePurposeInterceptor request uri{"+ request.toString()+"}");
        LOG.info("In PostHandle method of SinglePurposeInterceptor response {"+ response.toString()+"}");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        LOG.info("In afterCompletion method of SinglePurposeInterceptor request uri{"+ request.getRequestURI().toString()+"}");
        LOG.info("In afterCompletion method of SinglePurposeInterceptor request uri{"+ request.toString()+"}");
        LOG.info("In afterCompletion method of SinglePurposeInterceptor response {"+ response.toString()+"}");
    }

}
