package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    Transfer getTransferById(int id);

    Transfer createTransfer(int toAccountId, int fromAccountId, int transferAmount, boolean transferStatus);

    List<Transfer> transferHistory(int accountId);
}