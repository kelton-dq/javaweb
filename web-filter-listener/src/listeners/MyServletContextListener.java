/**
 * Copyright (C), 2020-2022, XDU
 * FileName: MyServletContextListener
 * Author: Dingq
 * Date: 2022/4/24 18:42
 * Description:
 */
package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext对象创建……");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext对象销毁……");
    }
}