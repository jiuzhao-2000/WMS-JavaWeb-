package com.jiuzhao73.dao;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBHelper {
    private final static String driver;
    private final static String url;
    private final static String name;
    private final static String pwd;

    static {
        InputStream is = DBHelper.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            System.out.println("db.properties加载失败");
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        name = properties.getProperty("name");
        pwd = properties.getProperty("password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("未找到数据库驱动！");
        }
    }

    //获取失败返回null对象
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, name, pwd);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return con;
    }

    public static PreparedStatement getPreparedStatement(Connection con, String sql, Object[] params) {
        PreparedStatement preState = null;
        try {
            preState = con.prepareStatement(sql);
            int paramsLen = params.length;
            for (int i = 0; i < paramsLen; i++) {
                preState.setObject(i + 1, params[i]);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return preState;
    }

    //增删改方法
    //sql执行失败返回-1
    public static int executeUpdate(Connection con, PreparedStatement preState) {
        int rst = -1;
        try {
            rst = preState.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return rst;
    }

    //查询方法
    //sql执行失败返回null对象
    public static ResultSet executeQuery(Connection con, PreparedStatement preState) {
        ResultSet rst = null;
        try {
            rst = preState.executeQuery();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return rst;
    }

    public static void close(Connection con, Statement state) {
        try {
            if (!state.isClosed())
                state.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            System.out.println("Statement关闭失败");
            return;
        }
        try {
            if (!con.isClosed())
                con.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            System.out.println("Connection关闭失败");
        }
    }

    @Test
    public void test() {
        Connection con = getConnection();
        PreparedStatement preState = getPreparedStatement(con, "select * from userTable;", new String[]{});
        ResultSet set = executeQuery(con, preState);
        try {
            while (set.next()) {
                System.out.println(set.getObject("ucode"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            close(con, preState);
        }
    }
}