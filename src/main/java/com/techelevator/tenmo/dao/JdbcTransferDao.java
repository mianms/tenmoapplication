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

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transfer getTransferById(int id) {
        Transfer transfer = null;
        String sql = "SELECT transfer_id, to_account_id, from_account_id, transfer_amount, transfer_status WHERE transfer_id = ?";

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


    public Transfer createTransfer(int toAccountId, int fromAccountId, int transferAmount, boolean transferStatus) {

        Transfer newTransfer = null;
        String sql = "INSERT INTO transfer (" +
                "account_from, account_to, transfer_amount, transfer_status) " +
                "VALUES (?, ?, ?, ?) RETURNING transfer_id";

        try {
            int newTransferId = this.jdbcTemplate.queryForObject(
                    sql,
                    Integer.class,
                    fromAccountId,
                    toAccountId,
                    transferAmount,
                    transferStatus);

            return getTransferById(newTransferId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public List<Transfer> transferHistory(int accountId) {
        List<Transfer> listOfTransfers = new ArrayList<>();
        String sql = "SELECT transfer_id, to_account_id, from_account_id, transfer_amount, transfer_status " +
                     "FROM transfer WHERE to_account_id = ? OR from_account_id = ?";
        SqlRowSet result = this.jdbcTemplate.queryForRowSet(sql, accountId, accountId);
        while (result.next()) {
            int id = result.getInt("transfer_id");
            int toAccountId = result.getInt("to_account_id");
            int fromAccountId = result.getInt("from_account_id");
            BigDecimal transferAmount = result.getBigDecimal("transfer_amount");
            Boolean transferStatus = true;
            Transfer transfer = new Transfer(id, toAccountId, fromAccountId, transferAmount, transferStatus);
            listOfTransfers.add(transfer);

        }
        return listOfTransfers;
    }

    private Transfer mapRowToTransfer(SqlRowSet trans) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(trans.getInt("transfer_id"));
        transfer.setFromAccountId(trans.getInt("account_from"));
        transfer.setToAccountId(trans.getInt("account_to"));
        transfer.setTransferAmount(trans.getBigDecimal("transfer_amount"));
        transfer.setTransferStatus(trans.getBoolean("transfer_status"));
        return transfer;
    }


}
