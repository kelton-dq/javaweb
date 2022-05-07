/**
 * Copyright (C), 2020-2022, XDU
 * FileName: CartController
 * Author: Dingq
 * Date: 2022/4/27 20:38
 * Description:
 */
package book.controller;

import book.pojo.Book;
import book.pojo.Cart;
import book.pojo.CartItem;
import book.pojo.User;
import book.service.CartItemService;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;

public class CartController {

    private CartItemService cartItemService;

    //加载当前用户的购物车信息
    public String index(HttpSession session){
        User user =(User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser", user);
        return "cart/cart";
    }

    public String addCart(Integer bookId, HttpSession session) {
        User user =(User) session.getAttribute("currUser");
        CartItem cartItem = new CartItem(new Book(bookId), 1, user);
        //如果存在，数量+1
        //否则在cart中新增
        cartItemService.addOrUpdateCartItem(cartItem, user.getCart());
        //方式一，跳转到购物车
        return "redirect:cart.do";
        //方式二，留在书城页，刷新顶栏购物车
//        Cart cart = cartItemService.getCart(user);
//        user.setCart(cart);
//        session.setAttribute("currUser", user);
//        return "redirect:book.do";
    }

    public String editCart(Integer cartItemId, Integer buyCount){
        cartItemService.updateCartItem(new CartItem(cartItemId, buyCount));
        return "redirect:cart.do";
    }

    public String editCart1(HttpSession session, Integer cartItemId, Integer buyCount){
        cartItemService.updateCartItem(new CartItem(cartItemId, buyCount));
        return cartInfo(session);
    }

    public String cartInfo(HttpSession session){
        User user =(User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser", user);
        Gson gson = new Gson();
        String json = gson.toJson(cart);
        return "json:" + json;
    }
}