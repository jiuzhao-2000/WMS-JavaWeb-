package com.jiuzhao73.entity.bean;

import com.jiuzhao73.entity.nullentity.Null;

public class Goods {
    private int id;
    private String name;
    private int number;
    private String className;
    private int classId;
    private final static NullGoods NULL_GOODS = new NullGoods();

    private static class NullGoods extends Goods implements Null {
    }

    public Goods() {
    }

    public Goods(int id, String name, int number, String className) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.className = className;
    }

    public static Goods getNullGoods() {
        return NULL_GOODS;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", className='" + className + '\'' +
                ", classId=" + classId +
                '}';
    }
}