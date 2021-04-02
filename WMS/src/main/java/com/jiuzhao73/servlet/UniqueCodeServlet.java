package com.jiuzhao73.servlet;

import com.alibaba.fastjson.JSONArray;
import com.jiuzhao73.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "UniqueCodeServlet")
public class UniqueCodeServlet extends HttpServlet {
    UserService userService;

    public UniqueCodeServlet() {
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        HashMap<String, String> rst = new HashMap<>();
        rst.put("rst", userService.queryIsUniqueCodeByCode(code) ? "t" : "f");
        PrintWriter writer = response.getWriter();
        writer.write(JSONArray.toJSONString(rst));
    }
}
