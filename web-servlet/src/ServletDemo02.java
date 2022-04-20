
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2020-2022, XDU
 * FileName: ServletDemo02
 * Author: Dingq
 * Date: 2022/4/19 19:51
 * Description: servlet生命周期
 */

public class ServletDemo02 extends HttpServlet {
    public ServletDemo02(){
        System.out.println("正在实例化……");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("正在初始化……");
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