package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.DaoException.DaoException;
import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getUsernameAndBalance() {

        Account myAccount = null;

        String sql = "SELECT tenmo_user.username, account.balance FROM tenmo_user JOIN account ON temo_user.user_id = account.user_id;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            if (results.next()) {
                myAccount = mapRowToAccount(results);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server/database", e);
        }
        return myAccount;
    }

  public Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setUsername(rs.getString("username"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }
}





