package com.jiuzhao73.servlet;

import com.jiuzhao73.constant.LogQueryParamsState;
import com.jiuzhao73.entity.bean.LogQueryParams;
import com.jiuzhao73.entity.bean.Page;
import com.jiuzhao73.entity.bean.WarehouseLog;
import com.jiuzhao73.service.WareHouseLogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QueryWarehouseLogServlet")
public class QueryWarehouseLogServlet extends HttpServlet {
    private final WareHouseLogService wareHouseLogService;

    public QueryWarehouseLogServlet() {
        wareHouseLogService = new WareHouseLogService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNum = request.getParameter("pageNum");
        int rst = 0;
        if (pageNum != null && !pageNum.isEmpty())
            rst = Integer.parseInt(pageNum);
        String name = request.getParameter("name");
        String isAdd = request.getParameter("isAdd");
        String beginTime = request.getParameter("beginTime");
        String lastTime = request.getParameter("lastTime");
        LogQueryParamsState state = LogQueryParams.getParamsState(name, isAdd, beginTime, lastTime);
        List<WarehouseLog> logs = new ArrayList<>();
        int count = 0;
        try {
            logs = execute(state, name, isAdd, beginTime, lastTime, rst);
            count = queryCount(state, name, isAdd, beginTime, lastTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Page page = new Page(count, rst);
        setAttribute(request, logs, page);
        request.getRequestDispatcher("/sys/checkout.jsp").forward(request, response);
    }

    private List<WarehouseLog> execute(LogQueryParamsState state, String name, String isAdd, String beginTime,
                                       String lastTime, int pageNum) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        switch (state) {
            case NO_NAME:
                return wareHouseLogService.getLogs(isAddToBool(isAdd), formatter.parse(beginTime),
                        formatter.parse(lastTime), pageNum);
            case ALL:
                return wareHouseLogService.getLogs(name, isAddToBool(isAdd), formatter.parse(beginTime),
                        formatter.parse(lastTime), pageNum);
            case NO_ONE:
                return wareHouseLogService.getLogs(pageNum);
            case NO_TIME:
                return wareHouseLogService.getLogs(name, isAddToBool(isAdd), pageNum);
            case HAVE_NAME:
                return wareHouseLogService.getLogs(name, pageNum);
            case HAVE_TIME:
                return wareHouseLogService.getLogs(formatter.parse(beginTime), formatter.parse(lastTime), pageNum);
            case NO_IS_ADD:
                return wareHouseLogService.getLogs(name, formatter.parse(beginTime),
                        formatter.parse(lastTime), pageNum);
            case HAVE_IS_ADD:
                return wareHouseLogService.getLogs(isAddToBool(isAdd), pageNum);
            default:
                return new ArrayList<>();
        }
    }

    private int queryCount(LogQueryParamsState state, String name, String isAdd, String beginTime,
                           String lastTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        switch (state) {
            case NO_NAME:
                return wareHouseLogService.getCount(isAddToBool(isAdd), formatter.parse(beginTime),
                        formatter.parse(lastTime));
            case ALL:
                return wareHouseLogService.getCount(name, isAddToBool(isAdd), formatter.parse(beginTime),
                        formatter.parse(lastTime));
            case NO_ONE:
                return wareHouseLogService.getCount();
            case NO_TIME:
                return wareHouseLogService.getCount(name, isAddToBool(isAdd));
            case HAVE_NAME:
                return wareHouseLogService.getCount(name);
            case HAVE_TIME:
                return wareHouseLogService.getCount(formatter.parse(beginTime), formatter.parse(lastTime));
            case NO_IS_ADD:
                return wareHouseLogService.getCount(name, formatter.parse(beginTime), formatter.parse(lastTime));
            case HAVE_IS_ADD:
                return wareHouseLogService.getCount(isAddToBool(isAdd));
            default:
                return 0;
        }
    }

    private void setAttribute(HttpServletRequest request, List<WarehouseLog> logs, Page page) {
        request.setAttribute("LogsList", logs);
        request.setAttribute("prePage", page.getPrePageNum());
        request.setAttribute("pageNum", page.getNowPageNum());
        request.setAttribute("lastPage", page.getMaxPageNum());
        request.setAttribute("sufPage", page.getSufPageNum());
        request.setAttribute("count", page.getCount());
    }

    private boolean isAddToBool(String isAdd) {
        return isAdd.equals("t");
    }
}
