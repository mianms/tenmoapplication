package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.DaoException.DaoException;
import com.techelevator.tenmo.model.Account;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Override
    public Account getAccountById(int id) {
        Account account = new Account();
        String sql = "SELECT username, balance FROM account WHERE account_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                account = mapRowToAccount(results);

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }


        return account;
    }
    @Override
    public Account updateFromAccount (BigDecimal transferAmount, int id){
        Account fromAccount = new Account();
        String sql = "UPDATE  account SET balance = balance - ? WHERE account_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql,transferAmount,id);

            if (rowsAffected ==0) {
                throw new DaoException("Zero rows affected");
            }
            fromAccount = getAccountById(id);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return fromAccount;

    }
    @Override
    public Account updateToAccount (BigDecimal transferAmount, int id){
        Account toAccount = new Account();
        String sql = "UPDATE  account SET balance = balance + ? WHERE account_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql,transferAmount,id);

            if (rowsAffected ==0) {
                throw new DaoException("Zero rows affected");
            }
            toAccount = getAccountById(id);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return toAccount;

    }


    public Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setUsername(rs.getString("username"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }

}





