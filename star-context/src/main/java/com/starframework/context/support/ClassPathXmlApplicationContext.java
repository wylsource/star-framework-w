package com.starframework.context.support;

import com.starframework.beans.factory.BeanFactory;
import com.starframework.beans.factory.support.DefaultListableBeanFactory;
import com.starframework.context.ApplicationContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: Wuyulong
 * @Date: 2018/7/7 17:19
 * @Description:
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private final BeanFactory beanFactory = new DefaultListableBeanFactory();
    /**
     * 存储路径及对应的文档
     */
    private Map<String, Document> docs = new ConcurrentHashMap<String, Document>(8);
    public ClassPathXmlApplicationContext(String xmlPaths){
        this(new String[]{xmlPaths});
    }

    public ClassPathXmlApplicationContext(String[] xmlPaths){
        Optional.ofNullable(xmlPaths).ifPresent(xmlPath ->
            Arrays.stream(xmlPath).forEach(xml -> initLoad(xml))
        );
    }

    private void initLoad(String xmlPath){
        // 加载xml文件
        this.docs.putIfAbsent(xmlPath, this.reader(xmlPath));
    }

    private Document reader(String xmlPath){
        // 将xml解析为document对象
        // 1.使用dom4j的解析器
        Document doc = null;
        try {
            SAXReader reader = new SAXReader(true);
            File xml = new File(xmlPath);
            if (Objects.isNull(xml)){
                // TODO document is not found exception
            }
            doc = reader.read(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
            // TODO exception
        }
        return doc;
    }
}
