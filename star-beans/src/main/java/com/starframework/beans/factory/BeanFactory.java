package com.starframework.beans.factory;

import com.starframework.beans.BeansException;
import com.starframework.beans.annotation.Nullable;

/**
 * @Auther: Wuyulong
 * @Date: 2018/7/7 09:31
 * @Description: bean工厂的顶级接口，定义bean的基本操作
 */
public interface BeanFactory {

    /**
     * 根据bean名称获取对应的实例
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object getBean(String beanName) throws BeansException;

    /**
     * 根据args参数找到bean名称对应的class构造方法，生成对应的bean实例
     * @param beanName
     * @param args
     * @return
     * @throws BeansException
     */
    Object getBean(String beanName, Object... args) throws BeansException;

    /**
     * 根据对应的requiredType查找对应的bean实例
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(Class<T> requiredType) throws BeansException;

    /**
     * 根据args参数找到requiredType对应的class构造方法，生成对应的bean实例
     * @param requiredType
     * @param args
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(Class<T> requiredType, Object... args) throws BeansException;

    /**
     * 根据对应的bean名称获取对应requiredType类型的实例
     * @param beanName
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(String beanName, @Nullable Class<T> requiredType) throws BeansException;

    /**
     * 判断容器中是否包含指定bean名称的bean实例
     * @param beanName
     * @return
     */
    boolean containsBean(String beanName);

    /**
     * 查看bean名称对应的实例是否是单例的
     * @param beanName
     * @return
     */
    boolean isSingleton(String beanName);

    /**
     * 查看bean名称对应的实例是否是多例的
     * @param beanName
     * @return
     */
    boolean isPrototype(String beanName);

    /**
     * 判断bean名称对应的class类型是否是指定类型
     * @param beanName
     * @param typeToMatch
     * @return
     */
    boolean isTypeMatch(String beanName, @Nullable Class<?> typeToMatch);

    /**
     * 获取bean名称对应的实例的class类型
     * @param beanName
     * @return
     */
    @Nullable
    Class<?> getType(String beanName);

    /**
     * 根据bean名称获取对应的实例别名（如果有）
     * @param beanName
     * @return
     */
    String[] getAliases(String beanName);
 }
