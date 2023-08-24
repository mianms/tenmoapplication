package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class UserController {

    private UserDao userDao;

    private UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/account/transfers/username", method = RequestMethod.GET)
    public List<User> validRecipients() {
        List<User> users = new ArrayList<>();
        return users;
    }
}
