/**
 * Copyright (C), 2020-2022, XDU
 * FileName: AxiosServlet01
 * Author: Dingq
 * Date: 2022/5/7 15:27
 * Description:
 */
package axios.servlet;

import axios.pojo.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/axios02.do")
public class AxiosServlet02 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();
        String str = null;
        StringBuffer stringBuffer = new StringBuffer();
        while((str = bufferedReader.readLine()) != null){
            stringBuffer.append(str);
        }
        str = stringBuffer.toString();
        System.out.println(str);//{"uname":"dd","pwd":"ok"}

        Gson gson = new Gson();
//        Gson gson = new GsonBuilder().create();
        //json -> java Object
        User user = gson.fromJson(str, User.class);
        System.out.println(user);//User{uname='dd', pwd='ok'}

        user.setUname("张三");

        //java Object -> json
        String json = gson.toJson(user);
        resp.setCharacterEncoding("utf-8");
        //MIME-TYPE
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}