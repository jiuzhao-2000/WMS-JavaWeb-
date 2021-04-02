package com.jiuzhao73.dao.userdao;

import com.jiuzhao73.dao.DBHelper;
import com.jiuzhao73.entity.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserHelper implements UserDao {
    @Override
    public String queryPasswordById(String id) {
        return queryByOneWhere("upwd", "uid", id);
    }

    @Override
    public String queryPowerById(String id) {
        return queryByOneWhere("upower", "uid", id);
    }

    @Override
    public User queryUserByCode(String code) {
        String sql = "select * from UserTable where ucode=?;";
        User user = User.getNullUser();
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, new String[]{code});
        ResultSet userSet = DBHelper.executeQuery(con, preState);
        return getUser(user, con, preState, userSet);
    }

    public User getUser(User user, Connection con, PreparedStatement preState, ResultSet userSet) {
        if (userSet == null)
            return User.getNullUser();
        try {
            if (userSet.next())
                user = getUserByResultSet(userSet);
            //读取到多条User报空对象
            if (userSet.next())
                return User.getNullUser();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBHelper.close(con, preState);
        }
        return user;
    }

    @Override
    public User queryUserById(int id) {
        String sql = "select * from UserTable where uid=?;";
        User user = User.getNullUser();
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, new Object[]{id});
        ResultSet userSet = DBHelper.executeQuery(con, preState);
        return getUser(user, con, preState, userSet);
    }

    @Override
    public boolean queryIsUniqueCodeByCode(String code) {
        String sql = "select count(1) from UserTable where ucode=?";
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, new String[]{code});
        ResultSet codeNum = DBHelper.executeQuery(con, preState);
        if (codeNum == null) return true;
        try {
            if (codeNum.next()) {
                if (codeNum.getInt(1) == 0)
                    return true;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBHelper.close(con, preState);
        }
        return false;
    }

    //queryEntity查询实体,term查询条件,val查询条件的值
    private String queryByOneWhere(String queryEntity, String term, String val) {
        String rst = "";
        String sql = "select " + queryEntity + " from UserTable where " + term + "=?";
        Connection con = DBHelper.getConnection();
        PreparedStatement statement = DBHelper.getPreparedStatement(con, sql, new String[]{val});
        ResultSet passwordSet = DBHelper.executeQuery(con, statement);
        if (passwordSet == null) return "";
        try {
            if (passwordSet.next())
                rst = passwordSet.getString(queryEntity);
            if (passwordSet.next())
                return "";
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBHelper.close(con, statement);
        }
        return rst;
    }

    @Override
    public boolean updatePasswordById(String id, String newPwd) {
        String sql = "update UserTable set upwd=? where uid=?";
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, new String[]{newPwd, id});
        int i = DBHelper.executeUpdate(con, preState);
        DBHelper.close(con, preState);
        return i == 1;
    }

    @Override
    public int queryUserNumberByOne(Object[] params) {
        int rst = 0;
        StringBuilder sql = new StringBuilder();
        sql.append("select count(1) from UserTable where 1=1 ");
        Object[] queryTerm = getIdAndCodeAndPowerParamsString(params, sql);
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql.toString(), queryTerm);
        ResultSet num = DBHelper.executeQuery(con, preState);
        try {
            if (num.next())
                rst = num.getInt(1);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBHelper.close(con, preState);
        }
        return rst;
    }

    @Override
    public List<User> queryUserPageByIdORCodeORPower(Object[] params, int pageNum) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from userTable where 1=1 ");
        ArrayList<User> rst = new ArrayList<>();
        Object[] queryParams = getIdAndCodeAndPowerParamsString(params, sql);
        sql.append("limit ").append((pageNum - 1) * 5).append(",5");
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql.toString(), queryParams);
        ResultSet querySet = DBHelper.executeQuery(con, preState);
        try {
            while (querySet.next())
                rst.add(getUserByResultSet(querySet));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBHelper.close(con, preState);
        }
        return rst;
    }

    @Override
    public boolean insertUserByUser(User user) {
        String sql = "insert into UserTable(ucode,upwd,upower) values (?,?,?);";
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql,
                new Object[]{user.getCode(), user.getPwd(), user.getPower()});
        int rst = DBHelper.executeUpdate(con, preState);
        DBHelper.close(con, preState);
        return rst == 1;
    }

    @Override
    public boolean deleteUserById(int id) {
        String sql = "delete from UserTable where uid=?";
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql, new Object[]{id});
        int rst = DBHelper.executeUpdate(con, preState);
        DBHelper.close(con, preState);
        return rst == 1;
    }

    @Override
    public boolean updateUserById(String id, Object[] params) {
        if (params.length != 2) return false;
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE UserTable SET ");
        ArrayList<Object> list = new ArrayList<>();
        if (params[0] != null) {
            sql.append("ucode=?,");
            list.add(params[0]);
        }
        if (params[1] != null) {
            sql.append("upwd=?,");
            list.add(params[1]);
        }
        sql.delete(sql.length() - 1, sql.length());
        sql.append(" WHERE uid = ?");
        list.add(id);
        System.out.println(sql.toString());
        System.out.println(id);
        System.out.println((String) params[1]);
        System.out.println((String) params[0]);
        Connection con = DBHelper.getConnection();
        PreparedStatement preState = DBHelper.getPreparedStatement(con, sql.toString(), list.toArray());
        int i = DBHelper.executeUpdate(con, preState);
        DBHelper.close(con, preState);
        return 1 == i;
    }

    private User getUserByResultSet(ResultSet rs) throws SQLException {
        return new User(rs.getInt("uid"),
                rs.getString("ucode"),
                rs.getString("upwd"),
                rs.getInt("upower"));
    }

    private Object[] getIdAndCodeAndPowerParamsString(Object[] params, StringBuilder sql) {
        ArrayList<Object> queryParams = new ArrayList<>();
        if (params.length != 3)
            return new Object[]{};
        //sql拼接，最多只有三个条件，直接使用三次if判断进行添加
        if (params[0] != null) {
            sql.append("and uid=? ");
            queryParams.add(params[0]);
        }
        if (params[1] != null) {
            sql.append("and ucode like ? ");
            queryParams.add("%" + params[1] + "%");
        }
        if (params[2] != null && params[2] instanceof Integer)
            sql.append(getPowerTermSQLStringByInt((int) params[2]));
        return queryParams.toArray();
    }

    //1.管理员==15
    //2.1<员工<15
    //3.浏览者==1
    private String getPowerTermSQLStringByInt(int power) {
        switch (power) {
            case 1:
                return "and upower=15 ";
            case 2:
                return "and upower<15 and upower>1 ";
            case 3:
                return ("and upower=1 ");
            default:
                return "";
        }
    }

}