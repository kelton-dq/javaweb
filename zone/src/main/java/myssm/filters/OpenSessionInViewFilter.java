/**
 * Copyright (C), 2020-2022, XDU
 * FileName: OpenSessionInViewFilter
 * Author: Dingq
 * Date: 2022/4/24 14:40
 * Description: 事务管理
 */
package myssm.filters;

import myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            TransactionManager.beginTrans();
//            System.out.println("开启事务……");
            filterChain.doFilter(servletRequest, servletResponse);
            TransactionManager.commit();
//            System.out.println("提交事务……");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                TransactionManager.rollback();
//                System.out.println("回滚事务……");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}