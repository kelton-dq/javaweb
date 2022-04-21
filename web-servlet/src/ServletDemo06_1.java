import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2020-2022, XDU
 * FileName: ServletDemo06_1
 * Author: Dingq
 * Date: 2022/4/21 15:27
 * Description: request保存作用域
 */

@WebServlet("/demo061")
public class ServletDemo06_1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //向request保存作用域存储数据
        req.setAttribute("name", "dd");
        resp.sendRedirect("demo062");
    }
}