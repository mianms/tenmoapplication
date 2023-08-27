package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.dao.UserTwoDao;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserTwo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("api")
public class UserController {

    private UserDao userDao;
    private UserTwoDao userTwoDao;

    public UserController(UserDao userDao, UserTwoDao userTwoDao) {
        this.userDao = userDao;
        this.userTwoDao = userTwoDao;
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
    @RequestMapping(path = "/mostUsers", method = RequestMethod.GET)
    public List<UserTwo> getUsers(Principal principal) {

        String username = principal.getName();
        List <UserTwo> mostUsers = userTwoDao.findAllButUser(username);

        return mostUsers;

    }


}
