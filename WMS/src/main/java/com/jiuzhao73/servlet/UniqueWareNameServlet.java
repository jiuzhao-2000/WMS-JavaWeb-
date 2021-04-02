package com.jiuzhao73.servlet;

import com.alibaba.fastjson.JSONArray;
import com.jiuzhao73.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "UniqueWareNameServlet")
public class UniqueWareNameServlet extends HttpServlet {
    private final GoodsService goodsService;

    public UniqueWareNameServlet() {
        goodsService = new GoodsService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name != null && !name.isEmpty()) {
            HashMap<String, String> rst = new HashMap<>();
            rst.put("result", goodsService.isUniqueName(name) ? "t" : "f");
            PrintWriter writer = response.getWriter();
            writer.write(JSONArray.toJSONString(rst));
        }
    }
}
