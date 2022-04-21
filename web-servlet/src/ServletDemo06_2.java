import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2020-2022, XDU
 * FileName: ServletDemo06_2
 * Author: Dingq
 * Date: 2022/4/21 15:31
 * Description:
 */
@WebServlet("/demo062")
public class ServletDemo06_2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object name = req.getAttribute("name");
        System.out.println("name = " + name);
        req.setAttribute("name", "qq");
        req.getRequestDispatcher("demo063").forward(req, resp);
    }
}