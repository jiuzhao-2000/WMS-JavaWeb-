package com.jiuzhao73.service;

import com.jiuzhao73.dao.userdao.UserDao;
import com.jiuzhao73.dao.userdao.UserHelper;
import com.jiuzhao73.entity.bean.User;
import com.jiuzhao73.entity.nullentity.Null;

import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService() {
        userDao = new UserHelper();
    }

    public User getUser(String code, String pwd) {
        User user = userDao.queryUserByCode(code);
        if ((!Null.isNull(user)) && pwd.equals(user.getPwd()))
            return user;
        return User.getNullUser();
    }

    public boolean updatePwdById(String id, String newPwd) {
        return userDao.updatePasswordById(id, newPwd);
    }

    public int queryUserNumberByOne(Object[] params) {
        return userDao.queryUserNumberByOne(params);
    }

    public List<User> queryUserPageByIdORCodeORPower(Object[] params, int pageNum) {
        return userDao.queryUserPageByIdORCodeORPower(params, pageNum);
    }

    public boolean queryIsUniqueCodeByCode(String code) {
        return userDao.queryIsUniqueCodeByCode(code);
    }

    public boolean insertUserByUser(User user) {
        return userDao.insertUserByUser(user);
    }

    public boolean deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    public User queryUserByCode(String code) {
        return userDao.queryUserByCode(code);
    }

    public User queryUserById(String id) {
        return userDao.queryUserById(Integer.parseInt(id));
    }

    public boolean updateUserById(String id, Object[] params) {
        return userDao.updateUserById(id, params);
    }
}
