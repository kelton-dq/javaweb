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
import myssm.util.StringUtil;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String oper = req.getParameter("oper");

        Integer pageNo = 1;
        HttpSession session = req.getSession();
        String keyword = null;
        if(!StringUtil.isEmpty(oper) && "search".equals(oper)){
            keyword = req.getParameter("keyword");
            if(StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        }else{
            String pageNoStr = req.getParameter("pageNo");
            if(!StringUtil.isEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj != null){
                keyword = (String) keywordObj;
            }else{
                keyword = "";
            }
        }


        session.setAttribute("pageNo", pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword, pageNo);
        int fruitCount = fruitDAO.getFruitCount(keyword);
        session.setAttribute("pageCount", (fruitCount + 4) / 5);
        //保存到session作用域
        session.setAttribute("fruitList", fruitList);
        //逻辑视图名称为index，thymeleaf会将逻辑视图名称对应到物理视图名称上去
        //物理视图名称 = view-prefix + 逻辑视图名称 + view-suffix
        super.processTemplate("index", req, resp);
    }
}