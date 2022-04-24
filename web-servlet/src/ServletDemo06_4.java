import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2020-2022, XDU
 * FileName: ServletDemo06_4
 * Author: Dingq
 * Date: 2022/4/21 16:14
 * Description: application(ServletContext)保存作用域
 */
@WebServlet("/demo064")
public class ServletDemo06_4 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object name = req.getServletContext().getAttribute("name");
        System.out.println("name = " + name);
    }
}