/**
 * Copyright (C), 2020-2022, XDU
 * FileName: DispatcherServlet
 * Author: Dingq
 * Date: 2022/4/22 19:38
 * Description: 调度器
 */
package myssm.myspringmvc;

import myssm.ioc.BeanFactory;
import myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

//    private Map<String, Object> beanMap = new HashMap<>();
    private BeanFactory beanFactory;

    public DispatcherServlet(){

    }

    public void init() throws ServletException {
        super.init();
//        将IOC初始化移动到ServletContext对象创建时完成
//        beanFactory  = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if(beanFactoryObj != null){
            beanFactory = (BeanFactory) beanFactoryObj;
        }else {
            throw new RuntimeException("IOC容器获取失败！");
        }
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

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}