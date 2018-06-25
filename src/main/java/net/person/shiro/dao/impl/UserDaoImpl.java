package net.person.shiro.dao.impl;

import net.person.shiro.dao.UserDao;
import net.person.shiro.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 2018/6/20.
 */
@Component
public class UserDaoImpl implements UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;


    public User getUserByUserName(String userName) {
        String sql="select user_name ,password from users where user_name = ?";
        List<User> list = jdbcTemplate.query(sql, new String[]{userName}, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user=new User();
                user.setUsername(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    public List<String> queryRolesByUserName(String userName) {
        String sql="select role_name from user_roles where user_name = ?";
        return jdbcTemplate.query(sql, new String[]{userName}, new RowMapper<String>() {
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("role_name");
            }
        });
    }
}
