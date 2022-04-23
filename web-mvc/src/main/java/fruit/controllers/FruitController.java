/**
 * Copyright (C), 2020-2022, XDU
 * FileName: FruitController
 * Author: Dingq
 * Date: 2022/4/22 15:59
 * Description:
 */
package fruit.controllers;

import fruit.dao.FruitDAO;
import fruit.dao.impl.FruitDAOImpl;
import fruit.pojo.Fruit;
import myssm.myspringmvc.ViewBaseServlet;
import myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FruitController{

    private FruitDAO fruitDAO = new FruitDAOImpl();

/*    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        try {
            super.init(servletContext);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }*/

    private String index(String oper, String keyword, Integer pageNo, HttpServletRequest req){

//        String oper = req.getParameter("oper");

//        Integer pageNo = 1;
        if(pageNo ==null){
            pageNo = 1;
        }
        HttpSession session = req.getSession();
//        String keyword = null;
        if (!StringUtil.isEmpty(oper) && "search".equals(oper)) {
//            keyword = req.getParameter("keyword");
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
//            String pageNoStr = req.getParameter("pageNo");
//            if (!StringUtil.isEmpty(pageNoStr)) {
//                pageNo = Integer.parseInt(pageNoStr);
//            }
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
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
//        super.processTemplate("index", req, resp);
        return "index";
    }

    private String add(String fname, Integer fprice, Integer fcount, String remark){
//        String fname = req.getParameter("fname");
//        int fprice = Integer.parseInt(req.getParameter("fprice"));
//        int fcount = Integer.parseInt(req.getParameter("fcount"));
//        String remark = req.getParameter("remark");

        fruitDAO.addFruit(new Fruit(0, fname, fprice, fcount, remark));

//        resp.sendRedirect("fruit.do");
        return "redirect:fruit.do";

    }

    private String del(Integer fid){
//        String fidStr = req.getParameter("fid");
//        if (!StringUtil.isEmpty(fidStr)) {
//            int fid = Integer.parseInt(fidStr);
//            fruitDAO.delFruit(fid);
//
////            resp.sendRedirect("fruit.do");
//            return "redirect:fruit.do";
//
//        }
        if(fid != null){
            fruitDAO.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String edit(Integer fid, HttpServletRequest req){
//        String fidStr = req.getParameter("fid");
//        if (!StringUtil.isEmpty(fidStr)) {
//            int fid = Integer.parseInt(fidStr);
//            Fruit fruit = fruitDAO.getFruitByFid(fid);
//            req.setAttribute("fruit", fruit);
////            super.processTemplate("edit", req, resp);
//            return "edit";
//        }
        if(fid != null){
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            req.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";
    }

    private String update(Integer fid, String fname, Integer fprice, Integer fcount, String remark){

//        int fid = Integer.parseInt(req.getParameter("fid"));
//        String fname = req.getParameter("fname");
//        int fprice = Integer.parseInt(req.getParameter("fprice"));
//        int fcount = Integer.parseInt(req.getParameter("fcount"));
//        String remark = req.getParameter("remark");

        fruitDAO.updateFruit(new Fruit(fid, fname, fprice, fcount, remark));
        //跳转回index页面
//        resp.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }
}