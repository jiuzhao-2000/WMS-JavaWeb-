package com.jiuzhao73.service;

import com.jiuzhao73.dao.warehouselogdao.WareHouseLogDao;
import com.jiuzhao73.dao.warehouselogdao.WareHouseLogHelper;
import com.jiuzhao73.entity.bean.WarehouseLog;

import java.util.Date;
import java.util.List;

public class WareHouseLogService {
    private final WareHouseLogDao wareHouseLogDao;

    public WareHouseLogService() {
        wareHouseLogDao = new WareHouseLogHelper();
    }

    public List<WarehouseLog> getLogs(int pageNum) {
        return wareHouseLogDao.getLogs(pageNum);
    }

    public List<WarehouseLog> getLogs(String name, int pageNum) {
        return wareHouseLogDao.getLogs(name, pageNum);
    }

    public List<WarehouseLog> getLogs(boolean isAdd, int pageNum) {
        return wareHouseLogDao.getLogs(isAdd, pageNum);
    }

    public List<WarehouseLog> getLogs(Date begin, Date last, int pageNum) {
        return wareHouseLogDao.getLogs(begin, last, pageNum);
    }

    public List<WarehouseLog> getLogs(String name, boolean isAdd, int pageNum) {
        return wareHouseLogDao.getLogs(name, isAdd, pageNum);
    }

    public List<WarehouseLog> getLogs(String name, Date begin, Date last, int pageNum) {
        return wareHouseLogDao.getLogs(name, begin, last, pageNum);
    }

    public List<WarehouseLog> getLogs(boolean isAdd, Date begin, Date last, int pageNum) {
        return wareHouseLogDao.getLogs(isAdd, begin, last, pageNum);
    }

    public List<WarehouseLog> getLogs(String name, boolean isAdd, Date begin, Date last, int pageNum) {
        return wareHouseLogDao.getLogs(name, isAdd, begin, last, pageNum);
    }

    public boolean addLog(WarehouseLog log) {
        return wareHouseLogDao.addLog(log);
    }

    public int getCount() {
        return wareHouseLogDao.getCount();
    }

    public int getCount(String name) {
        return wareHouseLogDao.getCount(name);
    }

    public int getCount(boolean isAdd) {
        return wareHouseLogDao.getCount(isAdd);
    }

    public int getCount(Date begin, Date last) {
        return wareHouseLogDao.getCount(begin, last);
    }

    public int getCount(String name, boolean isAdd) {
        return wareHouseLogDao.getCount(name, isAdd);
    }

    public int getCount(String name, Date begin, Date last) {
        return wareHouseLogDao.getCount(name, begin, last);
    }

    public int getCount(boolean isAdd, Date begin, Date last) {
        return wareHouseLogDao.getCount(isAdd, begin, last);
    }

    public int getCount(String name, boolean isAdd, Date begin, Date last) {
        return wareHouseLogDao.getCount(name, isAdd, begin, last);
    }
}
