package com.jiuzhao73.servlet;

import com.jiuzhao73.entity.bean.ClassInfo;
import com.jiuzhao73.service.ClassInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QueryClassInfoServlet")
public class QueryClassInfoServlet extends HttpServlet {
    private final ClassInfoService classService;

    public QueryClassInfoServlet() {
        classService = new ClassInfoService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ClassInfo> infos = classService.getAllClass();
        request.setAttribute("classes", infos);
        request.getRequestDispatcher("/sys/addNew.jsp").forward(request, response);
    }
}
