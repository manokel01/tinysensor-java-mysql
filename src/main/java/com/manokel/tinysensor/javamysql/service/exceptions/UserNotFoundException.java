package com.manokel.tinysensor.javamysql.service.exceptions;

import com.manokel.tinysensor.javamysql.model.User;

public class UserNotFoundException extends Exception {
    private final static long serialVersionUID = 1L;

    public UserNotFoundException(User user) {
        super("User with id " + user.getUserId() + "does not exist");
    }

    // in case for a custom message
    public UserNotFoundException(String s) {
        super(s);
    }
}
