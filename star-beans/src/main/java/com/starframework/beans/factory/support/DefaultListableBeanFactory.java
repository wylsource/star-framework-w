package com.starframework.beans.factory.support;

import com.starframework.beans.BeansException;
import com.starframework.beans.factory.BeanFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: Wuyulong
 * @Date: 2018/7/7 13:09
 * @Description: 默认的bean工厂
 */
public class DefaultListableBeanFactory implements BeanFactory {


    /**
     * 装载bean名称与bean实例
     */
    private final Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>(256);



    public Object getBean(String beanName) throws BeansException {
        return null;
    }

    public Object getBean(String beanName, Object... args) throws BeansException {
        return null;
    }

    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return null;
    }

    public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
        return null;
    }

    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return null;
    }

    public boolean containsBean(String beanName) {
        return false;
    }

    public boolean isSingleton(String beanName) {
        return false;
    }

    public boolean isPrototype(String beanName) {
        return false;
    }

    public boolean isTypeMatch(String beanName, Class<?> typeToMatch) {
        return false;
    }

    public Class<?> getType(String beanName) {
        return null;
    }

    public String[] getAliases(String beanName) {
        return new String[0];
    }
}
