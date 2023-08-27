package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.DaoException.DaoException;
import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferBodyDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.TransactionRequest;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferResponseBody;
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
    private TransferBodyDao transferBodyDao;
    public TransferController(TransferDao transferDao, AccountDao accountDao1, TransferServiceDao transferServiceDao1, TransferBodyDao transferBodyDao) {
        this.transferDao = transferDao;
        this.accountDao = accountDao1;
        this.transferServiceDao = transferServiceDao1;
        this.transferBodyDao = transferBodyDao;
    }




    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public TransferResponseBody addTransfer(Principal principal, @RequestBody TransactionRequest transactionRequest) {

        TransferResponseBody transferResponse = new TransferResponseBody();

        String fromUsername = principal.getName();
        int fromAccountID =  accountDao.getAccountId(fromUsername);

        String toUsername = transactionRequest.getUsername();
        int toAccountID = accountDao.getAccountId(toUsername);

        BigDecimal balance = accountDao.getAccountById(fromAccountID).getBalance();
        BigDecimal amount = transactionRequest.getAmount();


        Transfer createdTransfer = new Transfer(toAccountID,fromAccountID,amount);


        if  ( transferServiceDao.amountGreaterThanZero(amount) && transferServiceDao.amountLessThanBalance(amount,balance)) {

            //make transfer in DB and get transferID
            int transferId = transferDao.createTransfer( createdTransfer).getTransferId();

            //update balances
            accountDao.updateFromAccount(amount, fromAccountID);

            accountDao.updateToAccount(amount, toAccountID);

            //make TransferResponseBody
            transferResponse.setTransferId(transferId);
            transferResponse.setFrom(fromUsername);
            transferResponse.setTo(toUsername);
            transferResponse.setTransferAmount(amount);

        } else if (!transferServiceDao.amountGreaterThanZero(amount)) {

            throw new DaoException("Please enter an amount greater than zero.");

        } else if (!transferServiceDao.amountLessThanBalance(amount,balance)){

            throw new DaoException("Insufficient funds");


        }
        return transferResponse;
    }



    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
    public List <TransferResponseBody>  transferHistory (Principal principal) {

       String username = principal.getName();
       int id = transferDao.getAccountIdForTransfers(username);

      List <Transfer> transfers = transferDao.transferHistory(id);

      List <TransferResponseBody> newTransfers = transferBodyDao.getTransfersWithUsernames(transfers);

      return newTransfers;
    }


    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.GET)
    public TransferResponseBody  getTransfer (@PathVariable int id) {
        TransferResponseBody transfer = transferBodyDao.getTransferResponseByTransferId(id);
        return transfer;
    }
}
