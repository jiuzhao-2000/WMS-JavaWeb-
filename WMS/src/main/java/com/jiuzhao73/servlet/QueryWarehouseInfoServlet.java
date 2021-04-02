package com.jiuzhao73.servlet;

import com.jiuzhao73.constant.WarehouseParamsState;
import com.jiuzhao73.entity.bean.Goods;
import com.jiuzhao73.entity.bean.Page;
import com.jiuzhao73.service.ClassInfoService;
import com.jiuzhao73.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QueryWarehouseInfoServlet")
public class QueryWarehouseInfoServlet extends HttpServlet {
    private final ClassInfoService classService;
    private final GoodsService goodsService;

    public QueryWarehouseInfoServlet() {
        classService = new ClassInfoService();
        goodsService = new GoodsService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String classInfo = request.getParameter("classInfo");
        String pageNumString = request.getParameter("pageNum");
        int pageNum = pageNumString == null || pageNumString.isEmpty() ? 1 : Integer.parseInt(pageNumString);
        int count = 0;
        List<Goods> goodsList = new ArrayList<>();
        WarehouseParamsState state = getParamsState(name, classInfo);
        switch (state) {
            case ALL:
                count = goodsService.getCountByTerms(name, Integer.parseInt(classInfo));
                goodsList = goodsService.getGoodsPageByPageNum(name, Integer.parseInt(classInfo), pageNum);
                break;
            case NO_NAME:
                count = goodsService.getCountByTerms(Integer.parseInt(classInfo));
                goodsList = goodsService.getGoodsPageByPageNum(Integer.parseInt(classInfo), pageNum);
                break;
            case NO_CLASS_INFO:
                count = goodsService.getCountByTerms(name);
                goodsList = goodsService.getGoodsPageByPageNum(name, pageNum);
                break;
            case NO_NAME_CLASS_INFO:
                count = goodsService.getCountByTerms();
                goodsList = goodsService.getGoodsPageByPageNum(pageNum);
                break;
            default:
                System.out.println("状态枚举出现不存在值，请检查");
                break;
        }
        setAttribute(request, count, pageNum, goodsList);
        request.getRequestDispatcher("/sys/queryInfo.jsp").forward(request, response);
    }

    private void setAttribute(HttpServletRequest request, int count, int pageNum, List<Goods> goodsList) {
        Page note = new Page(count, pageNum);
        request.setAttribute("classes", classService.getAllClass());
        request.setAttribute("prePage", note.getPrePageNum());
        request.setAttribute("pageNum", note.getNowPageNum());
        request.setAttribute("lastPage", note.getMaxPageNum());
        request.setAttribute("sufPage", note.getSufPageNum());
        request.setAttribute("count", note.getCount());
        request.setAttribute("thingList", goodsList);
    }

    private WarehouseParamsState getParamsState(String name, String classInfo) {
        boolean isNullClassInfo = isNullClassInfo(classInfo);
        if (name == null || name.isEmpty())
            return isNullClassInfo ? WarehouseParamsState.NO_NAME_CLASS_INFO : WarehouseParamsState.NO_NAME;
        if (isNullClassInfo)
            return WarehouseParamsState.NO_CLASS_INFO;
        return WarehouseParamsState.ALL;
    }

    private boolean isNullClassInfo(String classInfo) {
        return classInfo == null || classInfo.isEmpty() || classInfo.equals("-1");
    }
}