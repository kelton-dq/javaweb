package cookies.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2020-2022, XDU
 * FileName: CookieServlet01
 * Author: Dingq
 * Date: 2022/4/28 20:52
 * Description:
 */
@WebServlet("/cookie01")
public class CookieServlet01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("uname", "dd");
        cookie.setMaxAge(20);
        //保存到浏览器
        resp.addCookie(cookie);
        req.getRequestDispatcher("/cookies/hello01.html").forward(req,resp);
    }
}