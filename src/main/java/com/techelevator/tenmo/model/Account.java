package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {


    private String username;
    private BigDecimal balance;

    public Account() {
    }

    public Account(String username, BigDecimal balance) {

        this.username = username;
        this.balance = balance;

    }



    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
