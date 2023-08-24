package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.DaoException.DaoException;
import com.techelevator.tenmo.model.Account;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getUsernameAndBalance(String username) {

        Account myAccount = new Account();
        String sql = "SELECT username, balance FROM account WHERE username = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()) {
                myAccount = mapRowToAccount(results);

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
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





