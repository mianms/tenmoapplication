package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    @RequestMapping(value = "/account/transfers/username", method = RequestMethod.GET)
    public List<User> validReceipients () {
        List <User> users = new ArrayList<>();
        return users;
    }
}
