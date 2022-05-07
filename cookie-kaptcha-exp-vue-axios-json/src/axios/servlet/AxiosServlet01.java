/**
 * Copyright (C), 2020-2022, XDU
 * FileName: AxiosServlet01
 * Author: Dingq
 * Date: 2022/5/7 15:27
 * Description:
 */
package axios.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/axios01.do")
public class AxiosServlet01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        System.out.println("uname = " + uname);
        System.out.println("pwd = " + pwd);

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(uname + "_" + pwd);

        //throw new NullPointerException("出现异常！");
    }
}