package com.techelevator.tenmo.dao;
import com.techelevator.tenmo.DaoException.DaoException;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {
    private JdbcTemplate jdbcTemplate;
//    private AccountDao accountDao;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Transfer getTransferByIdForTransfers(int id) {
        Transfer transfer = new Transfer();
        String sql = "SELECT transfer_id, account_to, account_from, transfer_amount FROM transfer WHERE transfer_id = ?";

        try {
            SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                transfer = mapRowToTransfer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("SQL syntax error", e);
        }
        return transfer;
    }


    @Override
    public Transfer createTransfer(Transfer transfer) {

        String sql = "INSERT INTO transfer (" +
                "account_from, account_to, transfer_amount) " +
                "VALUES (?, ?, ?) RETURNING transfer_id";

        try {
            int newTransferId = this.jdbcTemplate.queryForObject(
                    sql,
                    Integer.class,
                    transfer.getFromAccountId(),
                    transfer.getToAccountId(),
                    transfer.getTransferAmount());


            return getTransferByIdForTransfers(newTransferId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public List<Transfer> transferHistory(int accountId) {
        List<Transfer> listOfTransfers = new ArrayList<>();
        String sql = "SELECT transfer_id, account_from, account_to, transfer_amount " +
                     "FROM transfer WHERE account_from = ? OR account_to = ?";
        SqlRowSet result = this.jdbcTemplate.queryForRowSet(sql, accountId, accountId);
        while (result.next()) {
            int id = result.getInt("transfer_id");
            int toAccountId = result.getInt("account_from");
            int fromAccountId = result.getInt("account_to");
            BigDecimal transferAmount = result.getBigDecimal("transfer_amount");
            Transfer transfer = new Transfer(id, toAccountId, fromAccountId, transferAmount);
            listOfTransfers.add(transfer);

        }
        return listOfTransfers;
    }



    @Override
    public int getAccountIdForTransfers(String username) {
        String sql = "SELECT account_id FROM account WHERE username = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql,username);
        int id = 0;
        if (result.next()) {
            id = result.getInt("account_id");
        }
       return id;
    }




    private Transfer mapRowToTransfer(SqlRowSet trans) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(trans.getInt("transfer_id"));
        transfer.setFromAccountId(trans.getInt("account_from"));
        transfer.setToAccountId(trans.getInt("account_to"));
        transfer.setTransferAmount(trans.getBigDecimal("transfer_amount"));
        return transfer;
    }


}
