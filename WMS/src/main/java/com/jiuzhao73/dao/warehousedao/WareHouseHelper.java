package com.jiuzhao73.dao.warehousedao;

import com.jiuzhao73.dao.DBHelper;
import com.jiuzhao73.entity.bean.Goods;
import com.jiuzhao73.entity.bean.WarehouseLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WareHouseHelper implements WareHouseDao {
    @Override
    public int getCountByTerms() {
        String sql = "select count(1) from warehouse";
        return executeQuery(sql, new Object[]{});
    }

    @Override
    public int getCountByTerms(String name) {
        if (name == null || name.isEmpty())
            return -1;
        String sql = "select count(1) from warehouse where name like ?";
        return executeQuery(sql, new Object[]{"%" + name + "%"});
    }

    @Override
    public int getCountByTerms(int id) {
        String sql = "select count(1) from warehouse where classId=?";
        return executeQuery(sql, new Object[]{id});
    }

    @Override
    public int getCountByTerms(String name, int id) {
        if (name == null || name.isEmpty())
            return -1;
        String sql = "select count(1) from warehouse where name like ? and classId=?";
        return executeQuery(sql, new Object[]{"%" + name + "%", id});
    }

    private int executeQuery(String sql, Object[] params) {
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, params);
        ResultSet countSet = DBHelper.executeQuery(con, preState);
        int rst = 0;
        try {
            if (countSet.next()) {
                rst = countSet.getInt(1);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBHelper.close(con, preState);
        }
        return rst;
    }

    @Override
    public List<Goods> getGoodsPageByPageNum(int pageNum) {
        Connection con = DBHelper.getConnection();
        String sql = "select warehouse.id,warehouse.name,warehouse.number,commodityClass.name from warehouse," +
                "commodityClass where classId=commodityClass.id order by warehouse.id asc limit " +
                (pageNum - 1) * 5 + ",5";
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, new Object[]{});
        ResultSet goodsSet = DBHelper.executeQuery(con, preState);
        try {
            return getGoodsListBySet(goodsSet);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBHelper.close(con, preState);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Goods> getGoodsPageByPageNum(String name, int pageNum) {
        if (name == null || name.isEmpty())
            return new ArrayList<>();
        String sql = "select warehouse.id,warehouse.name,warehouse.number,commodityClass.name from warehouse," +
                "commodityClass where classId=commodityClass.id and warehouse.name like ?" +
                " order by warehouse.id asc limit " + (pageNum - 1) * 5 + ",5";
        return executeQueryGoods(sql, new Object[]{"%" + name + "%"});
    }

    @Override
    public List<Goods> getGoodsPageByPageNum(int id, int pageNum) {
        String sql = "select warehouse.id,warehouse.name,warehouse.number,commodityClass.name from warehouse," +
                "commodityClass where classId=commodityClass.id and classId=? order by warehouse.id asc limit " +
                (pageNum - 1) * 5 + ",5";
        return executeQueryGoods(sql, new Object[]{id});
    }

    @Override
    public List<Goods> getGoodsPageByPageNum(String name, int id, int pageNum) {
        if (name == null || name.isEmpty())
            return new ArrayList<>();
        String sql = "select warehouse.id,warehouse.name,warehouse.number,commodityClass.name from warehouse," +
                "commodityClass where classId=commodityClass.id and warehouse.name like ? and classId=? " +
                "order by warehouse.id asc limit " + (pageNum - 1) * 5 + ",5;";
        return executeQueryGoods(sql, new Object[]{"%" + name + "%", id});
    }

    @Override
    public boolean addNumberByName(String name, int num) {
        String sql = "update warehouse set warehouse.number=warehouse.number+? where warehouse.name=?;";
        Connection con = DBHelper.getConnection();
        try {
            con.setAutoCommit(false);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, new Object[]{num, name});
        int updateRst = DBHelper.executeUpdate(con, preState);
        try {
            preState.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            DBHelper.close(con, preState);
            return false;
        }
        boolean addRst = addLogs(con, name, num);
        try {
            con.commit();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        } finally {
            DBHelper.close(con, preState);
        }
        return updateRst == 1 && addRst;
    }

    @Override
    public boolean isUniqueName(String name) {
        String sql = "select count(1) from warehouse where name=?";
        return executeQuery(sql, new Object[]{name}) < 1;
    }

    @Override
    public boolean addNewGoodsByGoods(Goods goods) {
        String sql = "insert into warehouse(name,number,classId) values(?,?,?)";
        Connection con = DBHelper.getConnection();
        try {
            con.setAutoCommit(false);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql,
                new Object[]{goods.getName(), goods.getNumber(), goods.getClassId()});
        int rst = DBHelper.executeUpdate(con, preState);
        try {
            preState.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            DBHelper.close(con, preState);
            return false;
        }
        boolean addRst = addLogs(con, goods.getName(), goods.getNumber());
        try {
            con.commit();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        } finally {
            DBHelper.close(con, preState);
        }
        DBHelper.close(con, preState);
        return rst == 1 & addRst;
    }

    private List<Goods> getGoodsListBySet(ResultSet goodsSet) throws SQLException {
        List<Goods> rst = new ArrayList<>();
        while (goodsSet.next()) {
            int id = goodsSet.getInt("warehouse.id");
            String name = goodsSet.getString("warehouse.name");
            int number = goodsSet.getInt("number");
            String className = goodsSet.getString("commodityClass.name");
            rst.add(new Goods(id, name, number, className));
        }
        return rst;
    }

    private List<Goods> executeQueryGoods(String sql, Object[] params) {
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, params);
        ResultSet goodsSet = DBHelper.executeQuery(con, preState);
        try {
            return getGoodsListBySet(goodsSet);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBHelper.close(con, preState);
        }
        return new ArrayList<>();
    }

    private boolean addLogs(Connection con, String name, int num) {
        String sql = "insert into warehouseLog(name,time,isAdd,number) values(?,?,?,?);";
        WarehouseLog log = new WarehouseLog();
        log.setName(name);
        log.setTime(new Date(System.currentTimeMillis()));
        log.setAdd(num > 0);
        log.setNumber(Math.abs(num));
        Object[] params = new Object[]{log.getName(), log.getTime(), log.isAdd(), log.getNumber()};
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, params);
        int logRst = DBHelper.executeUpdate(con, preState);
        return logRst == 1;
    }

}