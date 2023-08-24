package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
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
    public double getBalance() {
        double balance = 0;

        String sql = "SELECT balance from account";
        SqlRowSet result = this.jdbcTemplate.queryForRowSet(sql);
        while (result.next()) {
            balance = result.getDouble("balance");
        }
        return balance;
    }


    private Account mapRowToAccount(SqlRowSet acc) {
        Account account = new Account();
        account.setAccountId(acc.getInt("account_id"));
        account.setUserId(acc.getInt("user_id"));
        account.setBalance(acc.getInt("balance"));
        account.setUsername(account.getUsername("username"));
        return account;
    }


    @Override
    public String getUsername() {
        return null;
    }
}
