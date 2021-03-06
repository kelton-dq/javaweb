/**
 * Copyright (C), 2020-2022, XDU
 * FileName: FruitController
 * Author: Dingq
 * Date: 2022/4/22 15:59
 * Description:
 */
package fruit.controllers;

import fruit.service.FruitService;
import fruit.service.impl.FruitServiceImpl;
import fruit.pojo.Fruit;
import myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FruitController {

    //    private FruitDAO fruitDAO = new FruitDAOImpl();
//    private FruitService fruitService = new FruitServiceImpl();
    private FruitService fruitService = null;
/*    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        try {
            super.init(servletContext);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }*/

    private String index(String oper, String keyword, Integer pageNo, HttpServletRequest req) {

//        String oper = req.getParameter("oper");

//        Integer pageNo = 1;
        if (pageNo == null) {
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

        List<Fruit> fruitList = fruitService.getFruitList(keyword, pageNo);
//        int fruitCount = fruitDAO.getFruitCount(keyword);
//        session.setAttribute("pageCount", (fruitCount + 4) / 5);
        session.setAttribute("pageCount", fruitService.getPageCount(keyword));
        //?????????session?????????
        session.setAttribute("fruitList", fruitList);
        //?????????????????????index???thymeleaf?????????????????????????????????????????????????????????
        //?????????????????? = view-prefix + ?????????????????? + view-suffix
//        super.processTemplate("index", req, resp);
        return "index";
    }

    private String add(String fname, Integer fprice, Integer fcount, String remark) {
//        String fname = req.getParameter("fname");
//        int fprice = Integer.parseInt(req.getParameter("fprice"));
//        int fcount = Integer.parseInt(req.getParameter("fcount"));
//        String remark = req.getParameter("remark");

        fruitService.addFruit(new Fruit(0, fname, fprice, fcount, remark));

//        resp.sendRedirect("fruit.do");
        return "redirect:fruit.do";

    }

    private String del(Integer fid) {
//        String fidStr = req.getParameter("fid");
//        if (!StringUtil.isEmpty(fidStr)) {
//            int fid = Integer.parseInt(fidStr);
//            fruitDAO.delFruit(fid);
//
////            resp.sendRedirect("fruit.do");
//            return "redirect:fruit.do";
//
//        }
        if (fid != null) {
            fruitService.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String edit(Integer fid, HttpServletRequest req) {
//        String fidStr = req.getParameter("fid");
//        if (!StringUtil.isEmpty(fidStr)) {
//            int fid = Integer.parseInt(fidStr);
//            Fruit fruit = fruitDAO.getFruitByFid(fid);
//            req.setAttribute("fruit", fruit);
////            super.processTemplate("edit", req, resp);
//            return "edit";
//        }
        if (fid != null) {
            Fruit fruit = fruitService.getFruitByFid(fid);
            req.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";
    }

    private String update(Integer fid, String fname, Integer fprice, Integer fcount, String remark) {

//        int fid = Integer.parseInt(req.getParameter("fid"));
//        String fname = req.getParameter("fname");
//        int fprice = Integer.parseInt(req.getParameter("fprice"));
//        int fcount = Integer.parseInt(req.getParameter("fcount"));
//        String remark = req.getParameter("remark");

        fruitService.updateFruit(new Fruit(fid, fname, fprice, fcount, remark));
        //?????????index??????
//        resp.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }
}