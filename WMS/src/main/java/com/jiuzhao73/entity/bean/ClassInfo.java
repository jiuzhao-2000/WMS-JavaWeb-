package com.jiuzhao73.entity.bean;

import com.jiuzhao73.entity.nullentity.Null;

public class ClassInfo implements Null {
    private int id;
    private String name;
    private final static NullClassInfo NULL_CLASS_INFO = new NullClassInfo();

    private static class NullClassInfo extends ClassInfo implements Null {
    }

    public ClassInfo getNullClassInfo() {
        return NULL_CLASS_INFO;
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

    public ClassInfo() {
    }

    public ClassInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
