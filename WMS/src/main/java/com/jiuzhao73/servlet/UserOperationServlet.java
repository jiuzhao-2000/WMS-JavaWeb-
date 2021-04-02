package com.jiuzhao73.servlet;

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

@WebServlet(name = "UserOperationServlet")
public class UserOperationServlet extends HttpServlet {
    private final UserService userService;

    public UserOperationServlet() {
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method == null || method.isEmpty()) {
            ErrorPage.gotoMethodErrorPage(response);
            return;
        }
        Object attribute = request.getSession().getAttribute(SessionInfo.USERNAME);
        if (!(attribute instanceof User) || attribute instanceof Null) {
            ErrorPage.gotoNoUserErrorPage(response);
            return;
        }
        User user = (User) attribute;
        if (!user.havePowerByString(method))
            ErrorPage.gotoPowerErrorPage(response);
        else
            executeByMethod(method, request, response);
    }

    private void executeByMethod(String method, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch (method) {
            case "c":
                create(request, response);
                break;
            case "r":
                read(request, response);
                break;
            case "u":
                update(request, response);
                break;
            case "d":
                delete(request, response);
                break;
            default:
                ErrorPage.gotoMethodErrorPage(response);
        }
    }

    //用户名唯一
    //密码不为空
    //权力至多只能和创建者一致
    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String pwd = request.getParameter("pwd");
        String[] powers = request.getParameterValues("power");
        User user = User.getUser(request);
        if (!(user.havePowerByArray(powers) || isUniqueCode(code)) || pwd.isEmpty())
            return;
        User add = new User();
        add.setCode(code);
        add.setPwd(pwd);
        add.setPower(User.powerArrayToInt(powers));
        userService.insertUserByUser(add);
        request.getRequestDispatcher("/sys/queryUser.do?pageNum=1").forward(request, response);
    }

    private void read(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userService.queryUserById(id);
        request.setAttribute("code", user.getCode());
        request.setAttribute("id", user.getId());
        request.setAttribute("pwd", user.getPwd());
        request.setAttribute("power", user.getPower());
        request.getRequestDispatcher("/sys/showUser.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String go = request.getParameter("go");
        if (go != null && !go.isEmpty()) {
            goUpdatePage(request, response);
            return;
        }
        String id = request.getParameter("id");
        String code = request.getParameter("code");
        String pwd = request.getParameter("pwd");
        System.out.println(id + ":" + code + ":" + pwd);
        userService.updateUserById(id, new Object[]{code, pwd});
        request.getRequestDispatcher("/sys/queryUser.do?pageNum=1").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        userService.deleteUserById(Integer.parseInt(id));
        request.getRequestDispatcher("/sys/queryUser.do?pageNum=1").forward(request, response);
    }

    private void goUpdatePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        Object attribute = request.getSession().getAttribute(SessionInfo.USERNAME);
        if (code == null || code.isEmpty() || Null.isNull(attribute))
            return;
        User user = (User) attribute;
        if (!user.haveUpdatePower())
            return;
        User queryUser = userService.queryUserByCode(code);
        request.setAttribute("id", queryUser.getId());
        request.setAttribute("pwd", queryUser.getPwd());
        request.getRequestDispatcher("/sys/alterUser.jsp").forward(request, response);
    }

    private boolean isUniqueCode(String code) {
        return userService.queryIsUniqueCodeByCode(code);
    }
}