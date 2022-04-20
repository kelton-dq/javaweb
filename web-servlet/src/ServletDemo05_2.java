import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2020-2022, XDU
 * FileName: ServletDemo05_2
 * Author: Dingq
 * Date: 2022/4/20 15:36
 * Description: 服务器内部转发与客户端重定向
 */

public class ServletDemo05_2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo052……");
        //重定向
        System.out.println("重定向：");
        resp.sendRedirect("demo053");
    }
}