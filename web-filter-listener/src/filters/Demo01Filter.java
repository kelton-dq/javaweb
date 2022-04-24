/**
 * Copyright (C), 2020-2022, XDU
 * FileName: Demo01Filter
 * Author: Dingq
 * Date: 2022/4/24 12:45
 * Description:
 */
package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/demo01.do")
@WebFilter("*.do")
public class Demo01Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("hello");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
        //响应之后的执行代码
        System.out.println("world");
    }

    @Override
    public void destroy() {

    }
}