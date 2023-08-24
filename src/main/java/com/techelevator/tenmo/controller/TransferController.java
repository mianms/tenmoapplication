package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.model.RegisterUserDTO;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class TransferController {


    @RequestMapping(value = "/account/transfers/send", method = RequestMethod.POST)
    public Transfer createTransfer (@Valid @RequestBody Transfer newTransfer) {

            return newTransfer;
    }

    @RequestMapping(value = "/account/transfers/username", method = RequestMethod.GET)
    public List<Transfer> getTransferHistory () {
        List <Transfer> transfers = new ArrayList<>();
        return transfers;
    }

    @RequestMapping(value = "/account/transfers/history/{transfer_id}", method = RequestMethod.GET)
    public Transfer getTransferById () {
        Transfer desiredTransfer = new Transfer();

        return desiredTransfer;
    }

}
