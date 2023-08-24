package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("isAuthenticated()")
@RestController
public class UserController {

    private UserDao userDao;

    private UserController(UserDao userDao) {
        this.userDao = userDao;
    }


}
