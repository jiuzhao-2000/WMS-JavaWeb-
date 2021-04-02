package com.jiuzhao73.filter;

import com.jiuzhao73.constant.SessionInfo;
import com.jiuzhao73.entity.bean.ErrorPage;
import com.jiuzhao73.entity.bean.User;
import com.jiuzhao73.entity.nullentity.Null;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SysFilter")
public class SysFilter implements Filter {
    public void destroy() {
        System.out.println("Sys未登录用户访问/sys目录文件过滤器销毁...");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request;
        HttpServletResponse response;
        if (!(req instanceof HttpServletRequest && resp instanceof HttpServletResponse))
            return;
        request = (HttpServletRequest) req;
        response = (HttpServletResponse) resp;
        User user = User.getUser(request);
        //user不为空，即用户已登录
        if (!Null.isNull(user))
            chain.doFilter(req, resp);
        else
            ErrorPage.gotoNoUserErrorPage(response);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("Sys未登录用户访问/sys目录文件过滤器开启...");
    }

}