/**
 * Copyright (C), 2020-2022, XDU
 * FileName: ClassPathXmlApplicationContext
 * Author: Dingq
 * Date: 2022/4/23 16:55
 * Description: 实现beanfactory
 */
package myssm.ioc;

import myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplicationContext implements BeanFactory {

    private Map<String, Object> beanMap = new HashMap<>();
    private static String path = "applicationContext.xml";

    public ClassPathXmlApplicationContext(){
        this(path);
    }

    public ClassPathXmlApplicationContext(String path){
        if(StringUtil.isEmpty(path)){
            throw new RuntimeException("IOC容器配置文件未指定！");
        }
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);

            NodeList beanNodeList = document.getElementsByTagName("bean");
            for(int i = 0; i < beanNodeList.getLength(); i++){
                Node beanNode = beanNodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element)beanNode;
                    String id = beanElement.getAttribute("id");
                    String className =beanElement.getAttribute("class");
                    Class beanClass = Class.forName(className);
                    //创建bean实例并保存到map容器
                    Object beanObj = beanClass.newInstance();
                    beanMap.put(id, beanObj);
                }
            }
            //组装bean之间的依赖关系
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String id = beanElement.getAttribute("id");
                    NodeList beanChildNodes = beanNode.getChildNodes();
                    for (int j = 0; j < beanChildNodes.getLength(); j++) {
                        Node beanChildNode = beanChildNodes.item(j);
                        if(beanChildNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())){
                            Element propertyElement = (Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");
                            //依赖对象
                            Object refObj = beanMap.get(propertyRef);
                            //原对象
                            Object beanObj = beanMap.get(id);
                            //传递对象
                            Class beanClazz = beanObj.getClass();
                            Field beanField = beanClazz.getDeclaredField(propertyName);
                            beanField.setAccessible(true);
                            beanField.set(beanObj, refObj);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}