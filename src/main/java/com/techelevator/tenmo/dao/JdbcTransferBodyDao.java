package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferResponseBody;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcTransferBodyDao implements TransferBodyDao {

    private JdbcTemplate jdbcTemplate;

    JdbcTransferBodyDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<TransferResponseBody> getTransfersWithUsernames(List<Transfer> transferList) {

        List <TransferResponseBody> newTransfers = new ArrayList<>();
        for (Transfer eachTransfer: transferList) {

            int transferId = eachTransfer.getTransferId();
            BigDecimal amount = eachTransfer.getTransferAmount();

            //get fromUsername

            int fromAccountId = eachTransfer.getFromAccountId();
            String fromUsername = getTransferFromUsername(transferId);

            //get toUsername
            int toAccountId = eachTransfer.getToAccountId();
            String toUsername = getTransferToUsername(transferId);

            //make TransferResponse

            TransferResponseBody transferResponse = new TransferResponseBody(transferId, amount,fromUsername,toUsername);

            newTransfers.add(transferResponse);

        }
        return newTransfers;
    }

    @Override
    public String getTransferFromUsername(int transferId) {
        String sql = "SELECT username FROM transfer JOIN account ON  transfer.account_from = account.account_id WHERE transfer_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transferId);
        String fromUsername = "";
        if (result.next()) {
            fromUsername = result.getString("username");
        }
        return fromUsername;
    }

    @Override
    public String getTransferToUsername(int transferId) {
        String sql = " SELECT username FROM transfer JOIN account ON  transfer.account_to = account.account_id WHERE transfer_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transferId);
        String toUsername = "";
        if (result.next()) {
            toUsername = result.getString("username");
        }
        return toUsername;
    }

    @Override
    public TransferResponseBody getTransferResponseByTransferId(int transferId) {

        String sql = "SELECT transfer_amount FROM transfer WHERE transfer_id = ? ";

        SqlRowSet transactionAmount = jdbcTemplate.queryForRowSet(sql,transferId);

        TransferResponseBody desiredTransfer = new TransferResponseBody();

       if (transactionAmount.next()) {



        BigDecimal amount = transactionAmount.getBigDecimal("transfer_amount");
        String toUsername = getTransferToUsername(transferId);
        String fromUsername = getTransferFromUsername(transferId);

        desiredTransfer.setTo(toUsername);
        desiredTransfer.setFrom(fromUsername);
        desiredTransfer.setTransferAmount(amount);
        desiredTransfer.setTransferId(transferId);



   }
        return desiredTransfer;

}
}
