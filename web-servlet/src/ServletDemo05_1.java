import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2020-2022, XDU
 * FileName: ServletDemo05_1
 * Author: Dingq
 * Date: 2022/4/20 15:33
 * Description: 服务器内部转发与客户端重定向
 */

public class ServletDemo05_1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo051……");
        //内部转发
        System.out.println("内部转发：");
        req.getRequestDispatcher("demo052").forward(req, resp);
    }
}