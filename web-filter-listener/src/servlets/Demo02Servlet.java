package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2020-2022, XDU
 * FileName: Demo01Servlet
 * Author: Dingq
 * Date: 2022/4/24 12:00
 * Description: filter
 */

@WebServlet("/demo02.do")
public class Demo02Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo02 service……");
        req.getRequestDispatcher("succ.html").forward(req, resp);
    }
}