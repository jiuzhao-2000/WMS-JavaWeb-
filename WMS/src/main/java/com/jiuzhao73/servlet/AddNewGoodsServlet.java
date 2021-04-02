package com.jiuzhao73.servlet;

import com.jiuzhao73.constant.SessionInfo;
import com.jiuzhao73.entity.bean.ErrorPage;
import com.jiuzhao73.entity.bean.Goods;
import com.jiuzhao73.entity.bean.User;
import com.jiuzhao73.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddNewGoodsServlet")
public class AddNewGoodsServlet extends HttpServlet {
    private final GoodsService goodsService;

    public AddNewGoodsServlet() {
        goodsService = new GoodsService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object attribute = request.getSession().getAttribute(SessionInfo.USERNAME);
        if (!(attribute instanceof User)) {
            return;
        }
        User user = (User) attribute;
        if (!user.haveCreatePower()) {
            ErrorPage.gotoPowerErrorPage(response);
            return;
        }
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        String classInfo = request.getParameter("classInfo");
        if (classInfo == null || classInfo.isEmpty() || classInfo.equals("-1")) {
            return;
        }
        Goods goods = new Goods();
        goods.setName(name);
        goods.setNumber(Integer.parseInt(number));
        goods.setClassId(Integer.parseInt(classInfo));
        if (goodsService.addNewGoodsByGoods(goods)) {
            System.out.println("添加成功");
            request.getRequestDispatcher("/sys/mainPage.jsp").forward(request, response);
        } else {
            System.out.println("添加失败");
        }
    }
}
