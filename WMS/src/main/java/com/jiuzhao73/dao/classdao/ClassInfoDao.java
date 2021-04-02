package com.jiuzhao73.dao.classdao;

import com.jiuzhao73.entity.bean.ClassInfo;

import java.util.List;

public interface ClassInfoDao {
    public String getNameById(int id);

    public int getIdByName(String name);

    public List<ClassInfo> getAllClass();
}
