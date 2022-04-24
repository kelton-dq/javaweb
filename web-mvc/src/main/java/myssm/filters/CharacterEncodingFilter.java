/**
 * Copyright (C), 2020-2022, XDU
 * FileName: CharacterEncodingFilter
 * Author: Dingq
 * Date: 2022/4/24 13:33
 * Description: 设置编码
 */
package myssm.filters;

import myssm.util.StringUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"*.do"}, initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class CharacterEncodingFilter implements Filter {

    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) {
        String encodingStr = filterConfig.getInitParameter("encoding");
        if(!StringUtil.isEmpty(encodingStr)){
            encoding = encodingStr;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        ((HttpServletRequest) servletRequest).setCharacterEncoding(encoding);

        filterChain.doFilter(servletRequest, servletResponse);

    }
}