/**
 * Copyright (C), 2020-2022, XDU
 * FileName: OrderController
 * Author: Dingq
 * Date: 2022/4/28 11:09
 * Description:
 */
package book.controller;

import book.pojo.Cart;
import book.pojo.OrderBean;
import book.pojo.User;
import book.service.CartItemService;
import book.service.OrderService;
import myssm.util.DateTrans;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderController {

    private OrderService orderService;
    private CartItemService cartItemService;

    public String checkout(HttpSession session){
        OrderBean orderBean = new OrderBean();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowStr = sdf.format(now);
        orderBean.setOrderNo(UUID.randomUUID().toString() + "_" + nowStr);

        orderBean.setOrderDate(now);

        User user =(User) session.getAttribute("currUser");
        orderBean.setOrderUser(user);

        orderBean.setOrderMoney(user.getCart().getTotalMoney());
        orderBean.setOrderStatus(0);

        orderService.addOrderBean(orderBean);

        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser", user);
        return "index";
    }


    public String getOrderList(HttpSession session){
        User currUser = (User) session.getAttribute("currUser");

        List<OrderBean> orderList = orderService.getOrderList(currUser);
        currUser.setOrderList(orderList);

        session.setAttribute("currUser",currUser);

        return "order/order";
    }

}