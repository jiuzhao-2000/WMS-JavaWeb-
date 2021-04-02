package com.jiuzhao73.servlet;

import com.jiuzhao73.constant.SessionInfo;
import com.jiuzhao73.entity.bean.ErrorPage;
import com.jiuzhao73.entity.bean.User;
import com.jiuzhao73.entity.bean.WarehouseLog;
import com.jiuzhao73.service.GoodsService;
import com.jiuzhao73.service.WareHouseLogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "AddGoodsServlet")
public class AddGoodsServlet extends HttpServlet {
    private final GoodsService goodsService;

    public AddGoodsServlet() {
        goodsService = new GoodsService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object attribute = request.getSession().getAttribute(SessionInfo.USERNAME);
        if (!(attribute instanceof User))
            return;
        User user = (User) attribute;
        if (!user.haveCreatePower()) {
            ErrorPage.gotoPowerErrorPage(response);
            return;
        }
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        if (isTrueParams(name, number)&&goodsService.addNumberByName(name, Integer.parseInt(number))) {
            System.out.println("添加成功");
            request.getRequestDispatcher("/sys/addWare.jsp").forward(request, response);
        } else {
            request.setAttribute("error","添加失败");
            request.getRequestDispatcher("/sys/addWare.jsp").forward(request, response);
        }
    }

    private boolean isTrueParams(String name, String number) {
        return !(name == null || name.equals("") || number == null);
    }
}