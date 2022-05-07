/**
 * Copyright (C), 2020-2022, XDU
 * FileName: SessionFilter
 * Author: Dingq
 * Date: 2022/4/28 17:42
 * Description:
 */
package z_book.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = {"*.do", "*.html"},
           initParams = {
            @WebInitParam(name = "bai", value = "/page.do?operate=refresh&page=user/login,/user.do,/book.do,/page.do?operate=refresh&page=user/regist")
            })
public class SessionFilter implements Filter {

    List<String> list;

    @Override
    public void init(FilterConfig filterConfig) {
        String bai = filterConfig.getInitParameter("bai");
        String[] strings = bai.split(",");
        list = Arrays.asList(strings);

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        Object currUserObj = session.getAttribute("currUser");

        String uri = httpServletRequest.getRequestURI();
        String queryString = httpServletRequest.getQueryString();
        String str;
        if(queryString != null && !uri.equals("/user.do")){
            str = uri + "?" + queryString;
        }else{
            str = uri;
        }
        if(list.contains(str)){
            chain.doFilter(httpServletRequest, httpServletResponse);
        }else{
            if(currUserObj == null){
                httpServletResponse.sendRedirect("page.do?operate=refresh&page=user/login");
            }else {
                chain.doFilter(httpServletRequest, httpServletResponse);
            }
        }

    }
}