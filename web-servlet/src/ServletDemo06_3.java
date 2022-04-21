import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2020-2022, XDU
 * FileName: ServletDemo06_3
 * Author: Dingq
 * Date: 2022/4/21 15:57
 * Description:
 */
@WebServlet("/demo063")
public class ServletDemo06_3 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object name = req.getAttribute("name");
        System.out.println("name = " + name);
        ServletContext application = req.getServletContext();
        application.setAttribute("name", "dq");
    }
}