package com.techelevator.tenmo.model;

public class Transfer {

    private int transferId;
    private int toAccountId;
    private int fromAccountId;
    private double transferAmount;
    private boolean transferStatus;


    public Transfer() {
    }

    public Transfer(int transferId, int toAccountId, int fromAccountId, int transferAmount, boolean transferStatus) {
        this.transferId = transferId;
        this.toAccountId = toAccountId;
        this.fromAccountId = fromAccountId;
        this.transferAmount = transferAmount;
        this.transferStatus = true;
    }

    public Transfer(int accountFrom, int accountTo, int transferAmount, boolean transferStatus) {
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

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public boolean isTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(boolean transferStatus) {
        this.transferStatus = transferStatus;
    }

}
