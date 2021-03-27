package com.papaxiong.config;

import com.papaxiong.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.MultipartConfigElement;

/**
 * @author zhaoqi
 * @since 2021-02-02
 */
@Configuration
@EnableTransactionManagement
public class SpringRegistryConfig {

    @Value("${springmvc.maxFileSize:10MB}")
    private String maxFileSize;

    @Value("${springmvc.maxRequestSize:10MB}")
    private String maxRequestSize;



    //文件上传
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse(maxFileSize));
        factory.setMaxRequestSize(DataSize.parse(maxRequestSize));
        return factory.createMultipartConfig();
    }

    //SpringUtils
    @Bean
    public SpringUtils springUtils() {
        return new SpringUtils();
    }



    // login
    @Bean
    public LoginFilter loginFilter() {
        return new LoginFilter();
    }

    @Bean
    public FilterRegistrationBean userFilterChain() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(loginFilter());
        registration.addUrlPatterns("/inspect/*");
        registration.setName("loginFilter");
        registration.setOrder(2);
        return registration;
    }




    //cros
    @Bean
    public FilterRegistrationBean corsFilterChain(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(corsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.addExposedHeader("token");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }
}
