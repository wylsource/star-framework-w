package com.starframework.context;

/**
 * @Auther: Wuyulong
 * @Date: 2018/7/7 19:10
 * @Description:
 */
public class TestBean {

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TestBean(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public TestBean() {
    }
}
