package com.starframework.beans.factory.support;

import com.starframework.beans.BeansException;
import com.starframework.beans.extend.entity.BeanProperty;
import com.starframework.beans.factory.BeanFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: Wuyulong
 * @Date: 2018/7/7 13:09
 * @Description: 默认的bean工厂
 */
public class DefaultListableBeanFactory implements BeanFactory {


    /**
     * 装载bean名称与bean的class
     */
    private final Map<String, Class<?>> beanMap = new ConcurrentHashMap<>(256);

    /**
     * 装载bean名称与其对应的property
     */
    private final Map<String, List<BeanProperty>> beanAndProperty = new ConcurrentHashMap<>(128);

    protected void addFactory(String id, Class<?> bean){
        beanMap.putIfAbsent(id, bean);
    }

    protected void addProperty(String id, List<BeanProperty> beanProperties){
        beanAndProperty.putIfAbsent(id, beanProperties);
    }

    /**
     * 初始化bean实例
     */
    protected void initBean(){

    }

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
