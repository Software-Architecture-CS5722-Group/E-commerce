package com.groupjn.orderservice.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestLoggerInterceptor implements HandlerInterceptor {

    private final Logger LOG = LoggerFactory.getLogger(RequestLoggerInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        String queryString = request.getQueryString();
        String remoteAddress = request.getRemoteAddr();
        String localAddress = request.getLocalAddr();
        String localName = request.getLocalName();
        String remoteHost = request.getRemoteHost();
        int remotePortNumber = request.getLocalPort();
        int localPortNumber = request.getLocalPort();
        String remoteUser = request.getRemoteUser();
        String method = request.getMethod();
        String pathInfo = request.getPathInfo();

        LOG.info("In PreHandle method of RequestPurposeInterceptor");
        LOG.info("Start of Logging PreHandle Request info" +
                " \n -------------------------------------------------------------------- \n" +
                "Request URI "+requestUri+"\n" +
                "Query String "+queryString+"\n" +
                "Remote Address "+remoteAddress+"\n" +
                "Local Address "+localAddress+"\n" +
                "Local name "+localName+"\n" +
                "RemoteHost Host "+remoteHost+"\n" +
                "Remote Port Number "+remotePortNumber+"\n" +
                "Local Port Number "+localPortNumber+"\n" +
                "Remote User "+remoteUser+"\n" +
                "Request Method "+method+"\n" +
                "Request Path Info "+pathInfo+"\n" +
                "End of Logging PreHandle Request info \n" +
                "-------------------------------------------------------------------- \n");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        LOG.info("In PostHandle method of RequestPurposeInterceptor");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        LOG.info("In afterCompletion method of RequestPurposeInterceptor");
    }


}
