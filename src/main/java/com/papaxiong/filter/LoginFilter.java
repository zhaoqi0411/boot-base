package com.papaxiong.filter;

import com.alibaba.fastjson.JSONObject;

import com.papaxiong.support.Wrapper;
import com.papaxiong.support.auth.AuthHolder;
import com.papaxiong.support.auth.JwtHelper;
import com.papaxiong.support.auth.SecretConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LoginFilter implements Filter {

    @Value("${login.accept.urls}")
    private String acceptUrl;

    private AntPathMatcher pathMatcher = new AntPathMatcher();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //静态资源无需过滤
        if (request.getRequestURI().toLowerCase().endsWith(".js")
                || request.getRequestURI().toLowerCase().endsWith(".css")
                || request.getRequestURI().toLowerCase().endsWith(".png")
                || request.getRequestURI().toLowerCase().endsWith(".jpg")
                || request.getRequestURI().toLowerCase().endsWith(".ttf")
                || request.getRequestURI().toLowerCase().endsWith(".html")
                || request.getRequestURI().toLowerCase().endsWith(".htm")
                || request.getRequestURI().toLowerCase().endsWith(".font")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (request.getRequestURI().startsWith("/test")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String acceptUrls = acceptUrl;
        if (StringUtils.isNotBlank(acceptUrls)) {
            List<String> acceptUrlList = Arrays.asList(acceptUrls.split(","));
            if (acceptUrls != null && acceptUrlList.size() > 0) {
                for (String acceptUrl : acceptUrlList) {
                    if (pathMatcher.match(acceptUrl, request.getRequestURI())) {
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    }
                }
            }
        }

        String token = request.getHeader("token");
        if(StringUtils.isBlank(token)){
            token=request.getParameter("token");
        }

        //判断jwt是否有效
        if (StringUtils.isNotBlank(token)) {
            //校验jwt是否有效,有效则返回json信息，无效则返回空
            String retJson = JwtHelper.validateLogin(token, SecretConstant.DATAKEY_USER);
            //retJSON为空则说明jwt超时或非法
            if (StringUtils.isNotBlank(retJson)) {
                //解析然后存在authhodler中
                AuthHolder.set("user", retJson);
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        //输出响应流
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(200);
        response.getWriter().write(JSONObject.toJSONString(Wrapper.error("99990", "登陆已失效！")));
        return;

    }

}
