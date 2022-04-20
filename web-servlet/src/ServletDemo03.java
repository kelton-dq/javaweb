import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Copyright (C), 2020-2022, XDU
 * FileName: ServletDemo03
 * Author: Dingq
 * Date: 2022/4/20 10:30
 * Description: 会话跟踪sessionId
 */

public class ServletDemo03 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session
        HttpSession httpSession = req.getSession();
        System.out.println("sessionId: " + httpSession.getId());
    }
}