package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Transfer createTransfer(int fromAccountId, int toAccountId, int transferAmount, boolean transferStatus) {
        Transfer newTransfer = null;
        String sql = "INSERT INTO transfer (" +
                "account_from, account_to, transfer_amount, transfer_status) " +
                "VALUES (?, ?, ?, ?) RETURNING transfer_id";

        Transfer transfer = new Transfer(fromAccountId, toAccountId, transferAmount, transferStatus);

        try {
            this.jdbcTemplate.queryForObject(
                    sql,
                    Integer.class,
                    transfer.getFromAccountId(),
                    transfer.getToAccountId(),
                    transfer.getTransferAmount(),
                    transfer.isTransferStatus());

            return transfer;

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }


    private Transfer mapRowToTransfer(SqlRowSet trans) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(trans.getInt("transfer_id"));
        transfer.setFromAccountId(trans.getInt("account_from"));
        transfer.setToAccountId(trans.getInt("account_to"));
        transfer.setTransferAmount(trans.getDouble("transfer_amount"));
        transfer.setTransferStatus(true);
        return transfer;
    }


}
