package com.jiuzhao73.dao.userdao;

import com.jiuzhao73.entity.bean.User;

import java.util.List;

public interface UserDao {
    String queryPasswordById(String id);

    String queryPowerById(String id);

    User queryUserByCode(String code);

    User queryUserById(int id);

    boolean queryIsUniqueCodeByCode(String code);

    boolean updatePasswordById(String id, String newPwd);

    int queryUserNumberByOne(Object[] params);

    List<User> queryUserPageByIdORCodeORPower(Object[] params, int pageNum);

    boolean insertUserByUser(User user);

    boolean deleteUserById(int id);

    boolean updateUserById(String id, Object[] params);
}