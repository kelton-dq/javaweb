
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2020-2022, XDU
 * FileName: ServletDemo02
 * Author: Dingq
 * Date: 2022/4/19 19:51
 * Description: servlet生命周期：实例化、初始化、服务、销毁
 */


@WebServlet(urlPatterns = {"/demo021"}, initParams = {@WebInitParam(name = "name", value = "dd")})
public class ServletDemo02 extends HttpServlet {
    public ServletDemo02(){
        System.out.println("正在实例化……");
    }

/*
init方法有两个：
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        this.init();
    }

    public void init() throws ServletException {
    }
可以重写init方法加入一些准备工作

*/

    @Override
    public void init() throws ServletException {
        System.out.println("正在初始化……");
        //获取初始化设置的数据
        ServletConfig servletConfig = getServletConfig();
        String hello = servletConfig.getInitParameter("hello");
        System.out.println("hello = " + hello);
        String name = servletConfig.getInitParameter("name");
        System.out.println("name = " + name);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("正在服务……");
    }

    @Override
    public void destroy() {
        System.out.println("正在销毁……");
    }
}