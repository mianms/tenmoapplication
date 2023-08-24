package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.UserTwo;

import java.util.List;

public interface UserTwoDao {

    List<UserTwo> findAllButUser(String username);
}
