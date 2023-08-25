package com.techelevator.tenmo.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class TransferService implements TransferServiceDao{

    // TODO: Transfer checks here
    @Override
    public boolean amountGreaterThanZero(BigDecimal amount) {

        if ( amount.compareTo(BigDecimal.ZERO) ==1) {
        return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean amountLessThanBalance(BigDecimal amount, BigDecimal balance) {
        if (amount.compareTo(balance) == 1) {

            return false;


        }else {
            return true;
        }
    }





}
