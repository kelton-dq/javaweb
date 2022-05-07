package book.controller;

import book.pojo.Cart;
import book.pojo.User;
import book.service.CartItemService;
import book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController {

    private UserService userService ;
    private CartItemService cartItemService;

    public String login(String uname , String pwd, HttpSession session){
        User user = userService.login(uname, pwd);
        if(user != null){
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            session.setAttribute("currUser", user);
            return "redirect:book.do";
        }else {
            return "user/login";
        }
    }

    public String regist(String verifyCode, String uname , String pwd, String email, HttpSession session, HttpServletResponse resp) throws IOException {
        Object kaptchaKeyObj = session.getAttribute("KAPTCHA_SESSION_KEY");
        if(kaptchaKeyObj == null || !verifyCode.equals(kaptchaKeyObj)){
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
//            out.println("<script language='javascript'>alert('验证码不正确！');window.location.href='page.do?operate=refresh&page=user/regist';</script>");
            out.println("<script language='javascript'>alert('验证码不正确！');</script>");
            return "user/regist";
//            return null;
        }else {
            if(verifyCode.equals(kaptchaKeyObj)){
                userService.regist(new User(uname, pwd, email, 0));
                return "user/login";
            }
        }
        return "user/login";
    }

    public String ckUname(String uname){
        User user = userService.getUser(uname);
        if(user != null){
            return "json:{\"uname\":\"1\"}";
        }else{
            return "json:{\"uname\":\"0\"}";
        }
    }
}
