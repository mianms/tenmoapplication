package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.service.TransferServiceDao;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("api")
public class TransferController {

    private TransferDao transferDao;
    private AccountDao accountDao;
    private TransferServiceDao transferServiceDao;
    public TransferController(TransferDao transferDao, AccountDao accountDao1, TransferServiceDao transferServiceDao1) {
        this.transferDao = transferDao;
        this.accountDao = accountDao1;
        this.transferServiceDao = transferServiceDao1;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public Transfer addTransfer(@Valid @RequestBody Transfer transfer) {

        int accountID = transfer.getFromAccountId();
        BigDecimal balance = accountDao.getAccountById(accountID).getBalance();
        BigDecimal amountToTransfer = transfer.getTransferAmount();
        Transfer createdTransfer = new Transfer();

      if  ( transferServiceDao.amountGreaterThanZero(amountToTransfer) && transferServiceDao.amountLessThanBalance(amountToTransfer,balance)) {
           createdTransfer = transferDao.createTransfer(transfer);

          //update balances
          BigDecimal transferAmount = createdTransfer.getTransferAmount();
          int fromId = createdTransfer.getFromAccountId();
          int toId = createdTransfer.getToAccountId();

          accountDao.updateFromAccount(transferAmount, fromId);

          accountDao.updateToAccount(transferAmount, toId);
      }


        return createdTransfer;
    }

    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
    public List <Transfer>  transferHistory (Principal principal) {
       String username = principal.getName();
       int id = transferDao.getAccountIdForTransfers(username);

      List <Transfer> transfers = transferDao.transferHistory(id);

      return transfers;
    }

    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.GET)
    public Transfer  getTransfer (@PathVariable int id) {
        Transfer transfer = transferDao.getTransferByIdForTransfers(id);
        return transfer;
    }
}
