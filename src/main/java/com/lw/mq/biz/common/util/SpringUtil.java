package com.lw.mq.biz.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    // 获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    // 通过name获取bean
    public static Object getBean(String name) {
        try {
            if (applicationContext != null) {
                return getApplicationContext().getBean(name);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    // 通过class获取bean
    public static <T> T getBean(Class<T> clz) {
        try {
            if (applicationContext != null) {
                return getApplicationContext().getBean(clz);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    // 通过class获取bean
    public static <T> Map<String, T> getBeans(Class<T> clz) {
        try {
            if (applicationContext != null) {
                return getApplicationContext().getBeansOfType(clz);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    // 通过name、class获取bean
    public static <T> T getBean(String name, Class<T> clz) {
        try {
            if (applicationContext != null) {
                return getApplicationContext().getBean(name, clz);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
