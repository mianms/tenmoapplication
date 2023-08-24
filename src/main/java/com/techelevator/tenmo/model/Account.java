package com.techelevator.tenmo.model;


public class Account {

    private int accountId;
    private int userId;
    private int balance;
    private String username;

    public Account() {
    }

    public Account(int accountId, int userId, int balance, String username) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
        this.username = username;

    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getUsername(String username) {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


}
