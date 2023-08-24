package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDao {

    public Transfer createTransfer(int fromAccountId, int toAccountId, int transferAmount, boolean transferStatus);
}