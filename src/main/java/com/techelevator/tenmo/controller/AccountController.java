package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.UserTwo;
import org.springframework.security.access.prepost.PreAuthorize;
import com.techelevator.tenmo.model.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("api")
public class AccountController {

    private AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    @RequestMapping(path = "/getBalance", method = RequestMethod.GET)
    public Account getUsernameAndBalance(Principal principal) {

        String username = principal.getName();
       Account newAccount = this.accountDao.getUsernameAndBalance(username);
        return newAccount;


    }



}
