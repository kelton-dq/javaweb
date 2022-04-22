/**
 * Copyright (C), 2020-2022, XDU
 * FileName: UpdateServlet
 * Author: Dingq
 * Date: 2022/4/21 20:14
 * Description: 更新数据库
 */
package fruit.servlets;

import fruit.dao.FruitDAO;
import fruit.dao.impl.FruitDAOImpl;
import fruit.pojo.Fruit;
import myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int fid = Integer.parseInt(req.getParameter("fid"));
        String fname = req.getParameter("fname");
        int fprice = Integer.parseInt(req.getParameter("fprice"));
        int fcount = Integer.parseInt(req.getParameter("fcount"));
        String remark = req.getParameter("remark");

        fruitDAO.updateFruit(new Fruit(fid, fname, fprice, fcount, remark));
        //跳转回index页面
        resp.sendRedirect("index");
//        req.getRequestDispatcher("index").forward(req, resp);
    }
}