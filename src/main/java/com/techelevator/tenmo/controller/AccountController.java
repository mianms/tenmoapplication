package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {


    private AccountDao accountDao;


    public AccountController(UserDao userDao, AccountDao accountDao, TransferDao transferDao) {

        this.accountDao = accountDao;

    }
    @RequestMapping(value = "/account/balance", method = RequestMethod.GET)
    public Account getBalance () {

       return accountDao.getUsernameAndBalance();
    }

}
