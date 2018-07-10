package com.starframework.beans.extend.entity;

/**
 * @Author: WuYuLong
 * @Date: Create in 16:07 2018/7/9
 * @DESC:
 */
public final class BeanProperty {

    private String name;

    private String value;

    private String ref;

    public BeanProperty(String name, String value, String ref) {
        this.name = name;
        this.value = value;
        this.ref = ref;
    }

    public BeanProperty() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
