/**
 * Copyright (C), 2020-2022, XDU
 * FileName: KaptchaServlet01
 * Author: Dingq
 * Date: 2022/5/5 10:52
 * Description:
 */
package kapatcha.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/kaptcha01")
public class KaptchaServlet01 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/kaptcha/hello02.html").forward(req,resp);
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("KAPTCHA_SESSION_KEY");
        System.out.println(obj);
    }
}