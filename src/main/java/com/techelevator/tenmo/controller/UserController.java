package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    //Return list of all users
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> users() {
        return userDao.findAll();
    }

    //Returns all users based on username
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public User findAllUsers(@RequestParam String name) {
        User users = userDao.findByUsername(name);
        return users;
    }

    //Returns userID by username
    @RequestMapping(value = "/username/{user_id}", method = RequestMethod.GET)
    public int getIdByUsername(@PathVariable String name) {
        int idByUsername = userDao.findIdByUsername(name);
        return idByUsername;
    }



}
