package com.jiuzhao73.dao.warehouselogdao;

import com.jiuzhao73.dao.DBHelper;
import com.jiuzhao73.entity.bean.WarehouseLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WareHouseLogHelper implements WareHouseLogDao {
    @Override
    public List<WarehouseLog> getLogs(int pageNum) {
        String sql = "select * from warehouseLog limit " + (pageNum - 1) * 5 + ",5;";
        return getWarehouseLogListByDB(sql, new Object[]{});
    }

    @Override
    public List<WarehouseLog> getLogs(String name, int pageNum) {
        String sql = "select * from warehouseLog where name like ? limit " + (pageNum - 1) * 5 + ",5;";
        return getWarehouseLogListByDB(sql, new Object[]{"%" + name + "%"});
    }

    @Override
    public List<WarehouseLog> getLogs(boolean isAdd, int pageNum) {
        String sql = "select * from warehouseLog where isAdd=? limit " + (pageNum - 1) * 5 + ",5;";
        return getWarehouseLogListByDB(sql, new Object[]{isAdd});
    }

    @Override
    public List<WarehouseLog> getLogs(Date begin, Date last, int pageNum) {
        String sql = "select * from warehouseLog where time between ? and ? limit " + (pageNum - 1) * 5 + ",5;";
        return getWarehouseLogListByDB(sql, new Object[]{begin, last});
    }

    @Override
    public List<WarehouseLog> getLogs(String name, boolean isAdd, int pageNum) {
        String sql = "select * from warehouseLog where name like ? and isAdd=? limit " + (pageNum - 1) * 5 + ",5;";
        return getWarehouseLogListByDB(sql, new Object[]{"%" + name + "%", isAdd});
    }

    @Override
    public List<WarehouseLog> getLogs(String name, Date begin, Date last, int pageNum) {
        String sql = "select * from warehouseLog where name like ? and time between ? and ? limit "
                + (pageNum - 1) * 5 + ",5;";
        return getWarehouseLogListByDB(sql, new Object[]{"%" + name + "%", begin, last});
    }

    @Override
    public List<WarehouseLog> getLogs(boolean isAdd, Date begin, Date last, int pageNum) {
        String sql = "select * from warehouseLog where isAdd=? and time between ? and ? limit "
                + (pageNum - 1) * 5 + ",5;";
        return getWarehouseLogListByDB(sql, new Object[]{isAdd, begin, last});
    }

    @Override
    public List<WarehouseLog> getLogs(String name, boolean isAdd, Date begin, Date last, int pageNum) {
        String sql = "select * from warehouseLog where name like ? and isAdd=? and time between ? and ? limit "
                + (pageNum - 1) * 5 + ",5;";
        return getWarehouseLogListByDB(sql, new Object[]{"%" + name + "%", isAdd, begin, last});
    }

    @Override
    public int getCount() {
        String sql = "select count(1) from warehouseLog";
        return getCountByDB(sql, new Object[]{});
    }

    @Override
    public int getCount(String name) {
        String sql = "select count(1) from warehouseLog where name like ?";
        return getCountByDB(sql, new Object[]{"%" + name + "%"});
    }

    @Override
    public int getCount(boolean isAdd) {
        String sql = "select count(1) from warehouseLog where isAdd=?";
        return getCountByDB(sql, new Object[]{isAdd});
    }

    @Override
    public int getCount(Date begin, Date last) {
        String sql = "select count(1) from warehouseLog where time between ? and ?";
        return getCountByDB(sql, new Object[]{begin, last});
    }

    @Override
    public int getCount(String name, boolean isAdd) {
        String sql = "select count(1) from warehouseLog where name like ? and isAdd=?";
        return getCountByDB(sql, new Object[]{"%" + name + "%", isAdd});
    }

    @Override
    public int getCount(String name, Date begin, Date last) {
        String sql = "select count(1) from warehouseLog where name like ? and time between ? and ?";
        return getCountByDB(sql, new Object[]{"%" + name + "%", begin, last});
    }

    @Override
    public int getCount(boolean isAdd, Date begin, Date last) {
        String sql = "select count(1) from warehouseLog where isAdd=? and time between ? and ?";
        return getCountByDB(sql, new Object[]{isAdd, begin, last});
    }

    @Override
    public int getCount(String name, boolean isAdd, Date begin, Date last) {
        String sql = "select count(1) from warehouseLog where name like ? and isAdd=? and time between ? and ?";
        return getCountByDB(sql, new Object[]{"%" + name + "%", isAdd, begin, last});
    }

    @Override
    public boolean addLog(WarehouseLog log) {
        String sql = "insert into warehouseLog(name,time,isAdd,number) values(?,?,?,?);";
        Connection con = DBHelper.getConnection();
        Object[] params = new Object[]{log.getName(), log.getTime(), log.isAdd(), log.getNumber()};
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, params);
        int rst = DBHelper.executeUpdate(con, preState);
        DBHelper.close(con, preState);
        return rst == 1;
    }

    private List<WarehouseLog> getWarehouseLogListByDB(String sql, Object[] params) {
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, params);
        ResultSet logs = DBHelper.executeQuery(con, preState);
        List<WarehouseLog> rst = new ArrayList<>();
        try {
            while (logs.next())
                rst.add(getWarehouseLogByResultSet(logs));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBHelper.close(con, preState);
        }
        return rst;
    }

    private WarehouseLog getWarehouseLogByResultSet(ResultSet set) {
        try {
            return new WarehouseLog(
                    set.getInt("id"),
                    set.getString("name"),
                    set.getDate("time"),
                    set.getBoolean("isAdd"),
                    set.getInt("number"));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return WarehouseLog.getNullWarehouseLog();
    }

    private int getCountByDB(String sql, Object[] params) {
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, params);
        ResultSet countSet = DBHelper.executeQuery(con, preState);
        try {
            if (countSet.next())
                return countSet.getInt(1);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBHelper.close(con, preState);
        }
        return 0;
    }

}