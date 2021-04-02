package com.jiuzhao73.dao.warehouselogdao;

import com.jiuzhao73.entity.bean.WarehouseLog;

import java.util.Date;
import java.util.List;

public interface WareHouseLogDao {
    List<WarehouseLog> getLogs(int pageNum);

    List<WarehouseLog> getLogs(String name, int pageNum);

    List<WarehouseLog> getLogs(boolean isAdd, int pageNum);

    List<WarehouseLog> getLogs(Date begin, Date last, int pageNum);

    List<WarehouseLog> getLogs(String name, boolean isAdd, int pageNum);

    List<WarehouseLog> getLogs(String name, Date begin, Date last, int pageNum);

    List<WarehouseLog> getLogs(boolean isAdd, Date begin, Date last, int pageNum);

    List<WarehouseLog> getLogs(String name, boolean isAdd, Date begin, Date last, int pageNum);

    int getCount();

    int getCount(String name);

    int getCount(boolean isAdd);

    int getCount(Date begin, Date last);

    int getCount(String name, boolean isAdd);

    int getCount(String name, Date begin, Date last);

    int getCount(boolean isAdd, Date begin, Date last);

    int getCount(String name, boolean isAdd, Date begin, Date last);

    boolean addLog(WarehouseLog log);

}
