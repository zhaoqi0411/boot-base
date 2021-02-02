package com.papaxiong.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


public class SpringUtils implements ApplicationContextAware {

    public static ApplicationContext ioc;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.ioc = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return ioc.getBean(beanClass);
    }
}
