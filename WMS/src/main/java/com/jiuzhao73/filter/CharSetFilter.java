package com.jiuzhao73.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "CharSetFilter")
public class CharSetFilter implements Filter {
    public void destroy() {
        System.out.println("字符串过滤器销毁...");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("字符串过滤器启动...");
    }

}