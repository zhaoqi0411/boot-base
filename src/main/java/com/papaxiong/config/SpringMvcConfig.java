package com.papaxiong.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.papaxiong.support.UTF8HttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoqi
 * @since 2021-02-02
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    public static final SerializerFeature[] features = {SerializerFeature.SkipTransientField,
            SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteEnumUsingToString};


    @Value("${springmvc.maxFileSize:10MB}")
    private String maxFileSize;

    @Value("${springmvc.maxRequestSize:10MB}")
    private String maxRequestSize;


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        WebMvcConfigurer.super.configureMessageConverters(converters);

        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.TEXT_HTML);

        // 1、ByteArrayHttpMessageConverter
        converters.add(new ByteArrayHttpMessageConverter());
        // 2、UTF8HttpMessageConverter
        UTF8HttpMessageConverter utf8Converter = new UTF8HttpMessageConverter();
        utf8Converter.setSupportedMediaTypes(supportedMediaTypes);
        converters.add(utf8Converter);
        // 3、MappingJackson2HttpMessageConverter
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(features);
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        fastJsonConverter.setSupportedMediaTypes(supportedMediaTypes);
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonConverter);
    }
}
