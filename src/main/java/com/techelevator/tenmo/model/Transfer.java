package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transfer {

    private int transferId;
    private int toAccountId;
    private int fromAccountId;
    private BigDecimal transferAmount;
    private boolean transferStatus;
    public Transfer() {}

    public Transfer(int transferId, int toAccountId, int fromAccountId, BigDecimal transferAmount, boolean transferStatus) {
        this.transferId = transferId;
        this.toAccountId = toAccountId;
        this.fromAccountId = fromAccountId;
        this.transferAmount = transferAmount;
        this.transferStatus = true;
    }


    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public boolean isTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(boolean transferStatus) {
        this.transferStatus = transferStatus;
    }

}
