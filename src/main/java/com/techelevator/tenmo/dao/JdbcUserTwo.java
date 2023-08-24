package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.UserTwo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcUserTwo implements UserTwoDao {
    private JdbcTemplate jdbcTemplate;


    public JdbcUserTwo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserTwo> findAllButUser(String username) {
        List<UserTwo> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash FROM tenmo_user WHERE username <> ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while (results.next()) {
            UserTwo user = mapRowToUserTwo(results);
            users.add(user);
        }
        return users;
    }

    private UserTwo mapRowToUserTwo(SqlRowSet rs) {
        UserTwo newUser = new UserTwo();
        newUser.setUsername(rs.getString("username"));
        return newUser;
    }
}
