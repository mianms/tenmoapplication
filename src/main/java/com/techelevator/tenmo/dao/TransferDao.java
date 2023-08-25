package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    Transfer getTransferByIdForTransfers(int id);


    Transfer createTransfer(Transfer transfer);

    List<Transfer> transferHistory(int accountId);

    int getAccountIdForTransfers(String username);

}