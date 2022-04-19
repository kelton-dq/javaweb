import fruit.dao.FruitDAO;
import fruit.dao.impl.FruitDAOImpl;
import fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2020-2022, XDU
 * FileName: AddServlet
 * Author: Dingq
 * Date: 2022/4/19 11:34
 * Description: Servlet组件，获取表单信息，调用DAO完成添加
 */

public class AddServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fname = req.getParameter("fname");
        Integer fprice = Integer.parseInt(req.getParameter("fprice"));
        Integer fcount = Integer.parseInt(req.getParameter("fcount"));
        String remark = req.getParameter("remark");

        FruitDAO fruitDAO = new FruitDAOImpl();
        boolean flag = fruitDAO.addFruit(new Fruit(2, fname, fprice, fcount, remark));

        System.out.println(flag ? "添加成功" : "添加失败");
    }
}