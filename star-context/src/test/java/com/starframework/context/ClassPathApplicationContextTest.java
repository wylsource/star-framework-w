package com.starframework.context;

import com.starframework.beans.factory.BeanFactory;
import com.starframework.beans.factory.support.DefaultListableBeanFactory;
import com.starframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Auther: Wuyulong
 * @Date: 2018/7/7 17:15
 * @Description: 测试加载配置文件
 */
public class ClassPathApplicationContextTest {

    @Test
    public void run(){
        ApplicationContext context = new ClassPathXmlApplicationContext("D:\\Life\\GitRepo\\Spring\\star-framework\\star-context\\src\\test\\resources\\ClassPathXmlApplicationContext.xml");
        TestUseBean bean = context.getBean(TestUseBean.class);
        String name = bean.getTestBean().getName();
        Assert.assertEquals(name, "zhangsan");
    }
}
