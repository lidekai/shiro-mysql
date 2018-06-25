package net.person.shiro.dao;

import net.person.shiro.model.User;

import java.util.List;

/**
 * Created by admin on 2018/6/20.
 */
public interface UserDao {
    User getUserByUserName(String userName);

    List<String> queryRolesByUserName(String userName);
}
