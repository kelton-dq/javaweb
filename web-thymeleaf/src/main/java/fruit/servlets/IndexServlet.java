/**
 * Copyright (C), 2020-2022, XDU
 * FileName: IndexServlet
 * Author: Dingq
 * Date: 2022/4/20 19:35
 * Description:
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//注解方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList();
        //保存到session作用域
        HttpSession session = req.getSession();
        session.setAttribute("fruitList", fruitList);
        //逻辑视图名称为index，thymeleaf会将逻辑视图名称对应到物理视图名称上去
        //物理视图名称 = view-prefix + 逻辑视图名称 + view-suffix
        super.processTemplate("index", req, resp);
    }
}