package com.jiuzhao73.servlet;

import com.jiuzhao73.constant.SessionInfo;
import com.jiuzhao73.entity.bean.User;
import com.jiuzhao73.entity.nullentity.Null;
import com.jiuzhao73.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private final UserService userService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        User user = userService.getUser(id, pwd);
        if(Null.isNull(user)){
            request.setAttribute("error","您的密码或者账号有错！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        else {
            request.getSession().setAttribute(SessionInfo.USERNAME,user);
            response.sendRedirect("/sys/mainPage.jsp");
        }

    }

    public LoginServlet() {
        userService =new UserService();
    }
}