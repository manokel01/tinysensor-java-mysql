package com.manokel.tinysensor.javamysql.service;

import com.manokel.tinysensor.javamysql.dao.IUserDAO;
import com.manokel.tinysensor.javamysql.dao.exceptions.DAOException;
import com.manokel.tinysensor.javamysql.dto.UserDTO;
import com.manokel.tinysensor.javamysql.model.User;
import com.manokel.tinysensor.javamysql.service.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements IUserService {
    private final IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User insertUser(UserDTO userToInsert) throws DAOException {
        if (userToInsert == null) return null;

        try {
            User user = mapUser(userToInsert);
            return userDAO.insert(user);

        } catch (DAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public User updateUser(UserDTO userToUpdate) throws
            DAOException, UserNotFoundException {

        try {
            if (userDAO.getByUserId(userToUpdate.getUserId()) == null) {
                throw new UserNotFoundException("User with id " + userToUpdate.getUserId()
                + " not found");
            }

            User user = mapUser(userToUpdate);
            return userDAO.update(user);
        } catch (DAOException | UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteUser(int userId) throws DAOException, UserNotFoundException {
        try {
            if (userDAO.getByUserId(userId) == null) {
                throw new UserNotFoundException("Teacher with id " + userId + "not found");
            }
            userDAO.delete(userId);

        } catch (DAOException | UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<User> getUsersByLastname(String lastname) throws DAOException {
        List<User> users = new ArrayList<>();
        if (lastname == null) return users;

        try {
            users = userDAO.getByUserLastname(lastname);
//            if (users.size() == 0) throw new UserNotFoundException("User with lastname " + lastname + " was not found.");
            return users;
        } catch (DAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private User mapUser(UserDTO dto) {
        return new User(dto.getUserId(), dto.getLastname(), dto.getFirstname(),
                dto.getEmail(), dto.getPostcode());
    }
}
