package com.jiuzhao73.dao.classdao;

import com.jiuzhao73.dao.DBHelper;
import com.jiuzhao73.entity.bean.ClassInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassInfoHelper implements ClassInfoDao {
    @Override
    public String getNameById(int id) {
        String sql = "select name from commodityClass where id=?";
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, new Object[]{id});
        ResultSet rst = DBHelper.executeQuery(con, preState);
        String name = "";
        try {
            if (rst != null && rst.next())
                name = rst.getString("name");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return name;
    }

    @Override
    public int getIdByName(String name) {
        String sql = "select id from commodityClass where name=?";
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, new Object[]{name});
        ResultSet rst = DBHelper.executeQuery(con, preState);
        int id = -1;
        try {
            if (rst != null && rst.next())
                id = rst.getInt("id");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return id;
    }

    @Override
    public List<ClassInfo> getAllClass() {
        String sql = "select * from commodityClass";
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, new Object[]{});
        ResultSet allSet = DBHelper.executeQuery(con, preState);
        ArrayList<ClassInfo> rst = new ArrayList<>();
        try {
            while (allSet.next()) {
                int id = allSet.getInt("id");
                String name = allSet.getString("name");
                rst.add(new ClassInfo(id, name));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return rst;
    }

}
