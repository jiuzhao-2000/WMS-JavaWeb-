package com.jiuzhao73.servlet;

import com.alibaba.fastjson.JSONArray;
import com.jiuzhao73.constant.SessionInfo;
import com.jiuzhao73.entity.bean.ErrorPage;
import com.jiuzhao73.entity.bean.User;
import com.jiuzhao73.entity.nullentity.Null;
import com.jiuzhao73.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "PwdServlet")
public class PwdServlet extends HttpServlet {
    private final UserService userService;

    public PwdServlet() {
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    //验证密码状态
    //1.正确：true
    //2.错误：false
    //3.session被注销:sessionError
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method.equals("")) {
            response.sendRedirect("/login.jsp");
        }
        if (method.equals("alterPwd")) {
            updatePwd(request, response);
        } else if (method.equals("confirmPwd")) {
            //密码正确
            HashMap<String, String> rst = new HashMap<>();
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            rst.put("rst", isTruePwd(request) ? "t" : "f");
            writer.write(JSONArray.toJSONString(rst));
        }
    }

    //修改密码
    private void updatePwd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = User.getUser(request);
        String newPwd = request.getParameter("newPwd");
        if (Null.isNull(user)) {
            response.setCharacterEncoding("utf-8");
            response.getWriter().print("<script>alert('default');</script>");
            return;
        }
        //修改成功
        if (userService.updatePwdById(Integer.toString(user.getId()), newPwd)) {
            //注销Session
            request.getSession().removeAttribute(SessionInfo.USERNAME);
            ErrorPage.gotoNoUserErrorPage(response);
        } else {
            System.out.println("修改失败");
        }
    }

    //判断密码正确与否
    private boolean isTruePwd(HttpServletRequest request) {
        User user = User.getUser(request);
        String oldPwd = request.getParameter("oldPwd");
        if (user.getPwd().equals(oldPwd)) {
            System.out.println("密码正确");
            return true;
        }
        System.out.println("密码错误！");
        return false;
    }
}