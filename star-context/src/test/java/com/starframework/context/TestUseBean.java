package com.starframework.context;

/**
 * @Auther: Wuyulong
 * @Date: 2018/7/7 19:12
 * @Description:
 */
public class TestUseBean {

    private TestBean testBean;

    public TestUseBean(TestBean testBean) {
        this.testBean = testBean;
    }

    public TestUseBean() {
    }

    public TestBean getTestBean() {
        return testBean;
    }

    public void setTestBean(TestBean testBean) {
        this.testBean = testBean;
    }
}
