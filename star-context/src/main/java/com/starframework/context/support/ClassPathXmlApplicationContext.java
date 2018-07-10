package com.starframework.context.support;

import com.starframework.beans.extend.entity.BeanProperty;
import com.starframework.beans.factory.BeanFactory;
import com.starframework.beans.factory.support.DefaultListableBeanFactory;
import com.starframework.context.ApplicationContext;
import com.starframework.context.constant.XmlAttribute;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: Wuyulong
 * @Date: 2018/7/7 17:19
 * @Description:
 */
public class ClassPathXmlApplicationContext extends DefaultListableBeanFactory implements ApplicationContext {

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
        resolveDocument();
        initBean();
    }

    /**
     * 解析document，利用反射构造实例对象
     */
    private void resolveDocument(){
        docs.values().stream()
                .forEach(document -> {
                    Element rootElement = document.getRootElement();
                    for(Iterator beans = rootElement.elementIterator(); beans.hasNext();){
                        Element rootNext = (Element) beans.next();
                        //与bean一级的节点
                        if (XmlAttribute.BEAN.equals(rootNext.getName())){
                            Attribute id = rootNext.attribute(XmlAttribute.BEAN_ID);
                            Attribute clazz = rootNext.attribute(XmlAttribute.BEAN_CLASS);
                            try {
                                this.addFactory(id.getValue(), Class.forName(clazz.getValue()));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                                // TODO exception
                            }
                            List<BeanProperty> properties = new ArrayList<>();
                            for (Iterator bean = rootNext.elementIterator();bean.hasNext();){
                                //property 一级
                                Element beanNext = (Element) bean.next();
                                if (XmlAttribute.BEAN_PROPERTY.equals(beanNext.getName())){
                                    //是property节点
                                    Attribute propertyName = beanNext.attribute(XmlAttribute.BEAN_PROPERTY_NAME);
                                    Attribute propertyValue = beanNext.attribute(XmlAttribute.BEAN_PROPERTY_VALUE);
                                    Attribute propertyRef = beanNext.attribute(XmlAttribute.BEAN_PROPERTY_REF);
                                    BeanProperty beanProperty = new BeanProperty(Objects.nonNull(propertyName)? propertyName.getValue():null,
                                            Objects.nonNull(propertyValue) ? propertyValue.getValue():null,
                                            Objects.nonNull(propertyRef) ? propertyRef.getValue():null);
                                    properties.add(beanProperty);
                                }
                            }
                            this.addProperty(id.getValue(), properties);
                        }
                    }
                });
    }
    /**
     * 加载xml并转换为document
     * @param xmlPath
     */
    private void initLoad(String xmlPath){
        // 加载xml文件
        this.docs.putIfAbsent(xmlPath, this.reader(xmlPath));
    }

    /**
     * 将对应的xml文件转换为document对象
     * @param xmlPath
     * @return
     */
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
