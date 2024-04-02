package com.ooselab.recruitmentsystemapi.userModule;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate template;

    public User Save(User user) {

        String sql = "insert into loginuser (username,email,password) values (?,?,?)";
        if (alreadyRegistered(user)) {
            user.message = "user already registred";
            return user;
        }
        int row = template.update(sql, user.userName, user.email, user.password);
        user.message = "success";
        return user;
    }

    public boolean alreadyRegistered(User user) {
        String sql = "SELECT COUNT(*) FROM loginuser WHERE email = ?";
        Integer count = template.queryForObject(sql, Integer.class, user.email);
        return count != null && count > 0;

    }

    public boolean userExists(User user) {
        String sql = "SELECT COUNT(*) FROM loginuser WHERE email = ? AND password = ?";
        Integer count = template.queryForObject(sql, Integer.class, user.email, user.password);
        return count != null && count > 0;
    }

    public List<User> getAllUser() {
        String sql = "select * from loginuser";
        RowMapper<User> rowMapper = new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet result, int rows) throws SQLException {
                User user = new User();
                user.id = result.getInt("ID");
                user.userName = result.getString("USERNAME");
                user.email = result.getString("EMAIL");
                user.password = result.getString("PASSWORD");
                user.isCompany = result.getBoolean("ISCOMPANY");
                return user;
            }

        };

        return template.query(sql, rowMapper);
    }
}
