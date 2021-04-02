package com.jiuzhao73.servlet;

import com.jiuzhao73.entity.bean.Page;
import com.jiuzhao73.entity.bean.User;
import com.jiuzhao73.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QueryUserServlet")
public class QueryUserServlet extends HttpServlet {
    private final UserService userService;

    public QueryUserServlet() {
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取页数，每页5条记录
        String page = request.getParameter("pageNum");
        int pageNum = toPageNum(page);
        //获取参数
        String queryId = request.getParameter("queryId");
        String queryCode = request.getParameter("queryCode");
        String flower = request.getParameter("flower");
        Object[] params = getParamsArrays(queryId, queryCode, flower);
        //查询 limit (pageNum-1)*5,5
        List<User> rst = userService.queryUserPageByIdORCodeORPower(params, pageNum);
        request.setAttribute("userList", rst);
        //转发到adminUser.jsp展示
        int allUserNumber = getUserNumber(params);
        setAdminUserPageParams(request, response, allUserNumber, pageNum);
        request.getRequestDispatcher("/sys/adminUser.jsp").forward(request, response);
    }

    private void setAdminUserPageParams(HttpServletRequest request, HttpServletResponse response, int count, int pageNum) {
        Page note = new Page(count, pageNum);
        request.setAttribute("prePage", note.getPrePageNum());
        request.setAttribute("pageNum", note.getNowPageNum());
        request.setAttribute("lastPage", note.getMaxPageNum());
        request.setAttribute("sufPage", note.getSufPageNum());
        request.setAttribute("count", note.getCount());
    }

    private Object[] getParamsArrays(String queryId, String queryCode, String flower) {
        Object[] rst = new Object[3];
        rst[0] = queryId == null || queryId.isEmpty() ? null : Integer.parseInt(queryId);
        rst[1] = queryCode == null || queryCode.isEmpty() ? null : queryCode;
        rst[2] = flower == null || flower.isEmpty() ? null : Integer.parseInt(flower);
        return rst;
    }

    private int toPageNum(String page) {
        return page.isEmpty() ? 1 : Math.max(Integer.parseInt(page), 1);
    }

    private int getUserNumber(Object[] params) {
        return userService.queryUserNumberByOne(params);
    }

}