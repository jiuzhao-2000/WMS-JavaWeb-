package com.jiuzhao73.servlet;

import com.jiuzhao73.constant.SessionInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BreakServlet")
public class BreakServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //注销session
        request.getSession().removeAttribute(SessionInfo.USERNAME);
        //重定向到login.jsp
        response.sendRedirect("/login.jsp");
    }
}