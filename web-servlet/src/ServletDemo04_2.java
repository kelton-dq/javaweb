import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2020-2022, XDU
 * FileName: ServletDemo04_2
 * Author: Dingq
 * Date: 2022/4/20 12:02
 * Description: session保存作用域-尝试读取数据（更换浏览器客户端则读取失败）
 */

public class ServletDemo04_2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object obj = req.getSession().getAttribute("name");
        System.out.println(obj);
        System.out.println("sessionId: " + req.getSession().getId());

    }
}