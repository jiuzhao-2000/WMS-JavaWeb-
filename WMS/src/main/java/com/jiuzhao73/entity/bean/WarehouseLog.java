package com.jiuzhao73.entity.bean;

import com.jiuzhao73.entity.nullentity.Null;

import java.util.Date;

public class WarehouseLog {
    private int id;
    private String name;
    private Date time;
    private boolean isAdd;
    private int number;

    private static final WarehouseLog NULL_WAREHOUSE_LOG = new NullWarehouseLog();

    private static class NullWarehouseLog extends WarehouseLog implements Null {
    }

    public static WarehouseLog getNullWarehouseLog() {
        return NULL_WAREHOUSE_LOG;
    }

    public WarehouseLog() {
    }

    public WarehouseLog(int id, String name, Date time, boolean isAdd, int number) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.isAdd = isAdd;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "WarehouseLog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", isAdd=" + isAdd +
                ", number=" + number +
                '}';
    }
}
