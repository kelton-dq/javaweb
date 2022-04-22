/**
 * Copyright (C), 2020-2022, XDU
 * FileName: AddServlet
 * Author: Dingq
 * Date: 2022/4/21 23:07
 * Description: 添加库存
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

@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String fname = req.getParameter("fname");
        int fprice = Integer.parseInt(req.getParameter("fprice"));
        int fcount = Integer.parseInt(req.getParameter("fcount"));
        String remark = req.getParameter("remark");

        fruitDAO.addFruit(new Fruit(0, fname, fprice, fcount, remark));

        resp.sendRedirect("index");
    }
}