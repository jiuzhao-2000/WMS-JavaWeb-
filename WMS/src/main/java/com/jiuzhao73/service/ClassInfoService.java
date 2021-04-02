package com.jiuzhao73.service;

import com.jiuzhao73.dao.classdao.ClassInfoDao;
import com.jiuzhao73.dao.classdao.ClassInfoHelper;
import com.jiuzhao73.entity.bean.ClassInfo;

import java.util.List;

public class ClassInfoService {
    private final ClassInfoDao classDao;

    public ClassInfoService() {
        classDao = new ClassInfoHelper();
    }

    public String getNameById(String id) {
        if (id != null) {
            return "";
        }
        int rst = Integer.parseInt(id);
        return classDao.getNameById(rst);
    }

    public int getIdByName(String name) {
        return classDao.getIdByName(name);
    }

    public List<ClassInfo> getAllClass() {
        return classDao.getAllClass();
    }
}