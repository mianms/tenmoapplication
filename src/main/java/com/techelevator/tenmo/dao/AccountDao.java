package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

     Account getUsernameAndBalance(String username);

     Account getAccountById(int id);

     Account updateFromAccount(BigDecimal transferAmount, int id);
     Account updateToAccount (BigDecimal transferAmount, int id);

}