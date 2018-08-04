package com.starframework.beans.factory.support;

import com.starframework.beans.BeansException;
import com.starframework.beans.exception.NotFoundBeanNameException;
import com.starframework.beans.extend.entity.BeanProperty;
import com.starframework.beans.factory.BeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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

    /**
     * 装载bean名称及bean实例
     */
    private final Map<String, Object> installMap = new ConcurrentHashMap<>(256);

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
        beanAndProperty.keySet().stream()
                .forEach(key -> install(key));
    }

    /**
     * 实例bean
     * @param beanName
     */
    private void install(String beanName){
        Class<?> clazz = beanMap.get(beanName);
        try {
            Object instance = clazz.newInstance();
            beanAndProperty.get(beanName).stream()
                    .forEach(beanProperty -> {
                        try {
                            Field field = instance.getClass().getDeclaredField(beanProperty.getName());
                            field.setAccessible(true);
                            if (Objects.nonNull(beanProperty.getValue())){
                                field.set(instance, beanProperty.getValue());
                            }else if (Objects.nonNull(beanProperty.getRef())){
                                String refBeanName = beanProperty.getRef();
                                Object install = installMap.get(refBeanName);
                                if (Objects.isNull(install)){
                                    install(refBeanName);
                                }
                                install = installMap.get(refBeanName);
                                field.set(instance, install);
                            }else {
                                // TODO 注入的值为null
                                field.set(instance, null);
                            }
                        } catch (NoSuchFieldException e) {
                            // TODO exception
                            e.printStackTrace();
                        } catch (IllegalAccessException e){
                            // TODO exception
                            e.printStackTrace();
                        }

                    });
            installMap.putIfAbsent(beanName, instance);
        } catch (InstantiationException e) {
            e.printStackTrace();
            // TODO exception
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            // TODO exception
        }
    }

    /**
     * 根据requiredType获取对应的beanName
     * @param requiredType
     * @param <T>
     * @return
     */
    private <T> String getBeanNameByRequiredType(final Class<T> requiredType){
        Optional<Map.Entry<String, Class<?>>> optional = beanMap.entrySet().stream()
                .filter(entry -> (entry.getValue().getTypeName().equals(requiredType.getTypeName()))
                ).findFirst();
        Map.Entry<String, Class<?>> entry = optional.get();
        if (Objects.isNull(entry)){
            return null;
        }
        return entry.getKey();
    }

    public Object getBean(String beanName) throws BeansException {
        return installMap.get(beanName);
    }

    public Object getBean(String beanName, Object... args) throws BeansException {
        if (Objects.nonNull(args)){
            throw new UnsupportedOperationException("DefaultListableBeanFactory does not support explicit bean creation arguments");
        }
        return installMap.get(beanName);
    }

    public <T> T getBean(Class<T> requiredType) throws BeansException {
        String beanName = getBeanNameByRequiredType(requiredType);
        if (Objects.isNull(beanName)){
            // TODO exception
            throw new NotFoundBeanNameException("Can not found the beanName from this class [" + requiredType + "]");
        }
        return (T)installMap.get(beanName);
    }

    public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
        return null;
    }

    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return null;
    }

    public boolean containsBean(String beanName) {
        return installMap.containsKey(beanName);
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
