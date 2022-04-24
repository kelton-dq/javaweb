/**
 * Copyright (C), 2020-2022, XDU
 * FileName: DispatcherServlet
 * Author: Dingq
 * Date: 2022/4/22 19:38
 * Description: 调度器
 */
package myssm.myspringmvc;

import myssm.io.BeanFactory;
import myssm.io.ClassPathXmlApplicationContext;
import myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

//    private Map<String, Object> beanMap = new HashMap<>();
    private BeanFactory beanFactory;

    public DispatcherServlet(){

    }

    public void init() throws ServletException {
        super.init();
        beanFactory  = new ClassPathXmlApplicationContext();
/*
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);

            NodeList beans = document.getElementsByTagName("bean");
            for(int i = 0; i < beans.getLength(); i++){
                Node bean = beans.item(i);
                if(bean.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element)bean;
                    String id = beanElement.getAttribute("id");
                    String className =beanElement.getAttribute("class");
                    Class controllerBeanClass = Class.forName(className);
                    Object beanObj = controllerBeanClass.newInstance();

//                    Method setServletContext = controllerBeanClass.getDeclaredMethod("setServletContext", ServletContext.class);
//                    setServletContext.invoke(beanObj, this.getServletContext());

                    beanMap.put(id, beanObj);
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
        }
*/

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
        //定位controller
        String servletPath = req.getServletPath().substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastDotIndex);
//        Object controllerBeanObj = beanMap.get(servletPath);
        Object controllerBeanObj = beanFactory.getBean(servletPath);
        //定位operate
        String operate = req.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }
        //获取并执行方法（与http相关的操作提取到方法外，方法内只执行数据操作，获取数据和页面渲染、重定向均提取出来）
        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for(Method method : methods){
                if(operate.equals(method.getName())){
                    //1.获取请求参数
                    Parameter[] parameters = method.getParameters();

                    Object[] values = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        if("req".equals(parameterName)){
                            values[i] = req;
                        }else if("resp".equals(parameterName)){
                            values[i] = resp;
                        }else if("session".equals(parameterName)){
                            values[i] = req.getSession();
                        }else{
                            String value = req.getParameter(parameterName);
                            String typeName = parameter.getType().getName();

                            Object parameterObj = value;
                            if(parameterObj != null && "java.lang.Integer".equals(typeName)){
                                parameterObj = Integer.parseInt(value);
                            }
                            values[i] = parameterObj;
                        }
                    }

                    //2.controller组件中方法的调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, values);
                    //3.视图处理
                    String methodReturnStr = (String) returnObj;
                    if(methodReturnStr.startsWith("redirect:")){
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        resp.sendRedirect(redirectStr);
                    }else {
                        super.processTemplate(methodReturnStr, req, resp);
                    }
                    return;
                }
            }
            throw new RuntimeException("operate值非法！");

//            Method method = controllerBeanObj.getClass().getDeclaredMethod(operate, HttpServletRequest.class);
//            if(method != null){
//                //1.获取请求参数
//
//                //2.controller组件中方法的调用
//                method.setAccessible(true);
//                Object returnObj = method.invoke(controllerBeanObj, req);
//                //3.视图处理
//                String methodReturnStr = (String) returnObj;
//                if(methodReturnStr.startsWith("redirect:")){
//                    String redirectStr = methodReturnStr.substring("redirect:".length());
//                    resp.sendRedirect(redirectStr);
//                }else {
//                    super.processTemplate(methodReturnStr, req, resp);
//                }
//
//            }else{
//                throw new RuntimeException("operate值非法！");
//            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}