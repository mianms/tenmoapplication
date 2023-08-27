package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferResponseBody;

import java.util.List;

public interface TransferBodyDao {
    List<TransferResponseBody> getTransfersWithUsernames (List <Transfer> transferList);

    String  getTransferFromUsername (int transferId);
    String getTransferToUsername (int transferId);

    TransferResponseBody getTransferResponseByTransferId (int transferId);
}
