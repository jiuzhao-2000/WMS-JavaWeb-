package com.jiuzhao73.entity.bean;

import com.jiuzhao73.constant.SessionInfo;
import com.jiuzhao73.entity.nullentity.Null;

import javax.servlet.http.HttpServletRequest;

public class User {
    private int id;
    private String code;
    private String pwd;
    private int power;
    //空对象
    private static final User NULL_USER = new NullUser();

    //表示NullUser
    private static class NullUser extends User implements Null {
    }

    //单例模式
    public static User getNullUser() {
        return NULL_USER;
    }

    public User(int id, String code, String pwd, int power) {
        this.id = id;
        this.code = code;
        this.pwd = pwd;
        this.power = power;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean haveReadPower() {
        return (power & 0b1) == 0b1;
    }

    public boolean haveCreatePower() {
        return (power & 0b10) == 0b10;
    }

    public boolean haveUpdatePower() {
        return (power & 0b100) == 0b100;
    }

    public boolean haveDeletePower() {
        return (power & 0b1000) == 0b1000;
    }

    public boolean havePowerByChar(char c) {
        switch (c) {
            case 'c':
            case 'C':
                return haveCreatePower();
            case 'r':
            case 'R':
                return haveReadPower();
            case 'u':
            case 'U':
                return haveUpdatePower();
            case 'd':
            case 'D':
                return haveDeletePower();
            default:
                return false;
        }
    }

    public boolean havePowerByString(String s) {
        switch (s) {
            case "c":
            case "C":
                return haveCreatePower();
            case "r":
            case "R":
                return haveReadPower();
            case "u":
            case "U":
                return haveUpdatePower();
            case "d":
            case "D":
                return haveDeletePower();
            default:
                return false;
        }
    }

    public boolean havePowerByInt(int power) {
        switch (power) {
            case 1:
                return haveReadPower();
            case 2:
                return haveCreatePower();
            case 4:
                return haveUpdatePower();
            case 8:
                return haveDeletePower();
            default:
                return false;
        }
    }

    public boolean havePowerByArray(String[] powers) {
        for (String power : powers) {
            if (!havePowerByString(power))
                return false;
        }
        return true;
    }

    public static User getUser(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(SessionInfo.USERNAME);
        if (!(attribute instanceof User) || Null.isNull(attribute))
            return User.getNullUser();
        return (User) attribute;
    }

    public static int powerArrayToInt(String[] val) {
        int rst = 0;
        for (String s : val)
            rst += powerToInt(s);
        return rst;
    }

    public static int powerToInt(String val) {
        switch (val) {
            case "c":
            case "C":
                return 2;
            case "r":
            case "R":
                return 1;
            case "u":
            case "U":
                return 4;
            case "d":
            case "D":
                return 8;
            default:
                return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User compare = (User) obj;
            return compare.getPwd().equals(pwd) && compare.getCode() == code;
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", pwd='" + pwd + '\'' +
                ", power=" + power +
                '}';
    }
}
