package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Transfer {

    @JsonProperty("transfer_id")
    private int transferId;
    @JsonProperty("account_to")
    private int toAccountId;
    @JsonProperty("account_from")
    private int fromAccountId;
    @JsonProperty("transfer_amount")
    private BigDecimal transferAmount;


    public Transfer() {
    }

    public Transfer(int transferId, int toAccountId, int fromAccountId, BigDecimal transferAmount) {
        this.transferId = transferId;
        this.toAccountId = toAccountId;
        this.fromAccountId = fromAccountId;
        this.transferAmount = transferAmount;
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



}
