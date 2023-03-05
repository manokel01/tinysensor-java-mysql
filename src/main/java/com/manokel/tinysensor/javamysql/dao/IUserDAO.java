package com.manokel.tinysensor.javamysql.dao;

import com.manokel.tinysensor.javamysql.dao.exceptions.DAOException;
import com.manokel.tinysensor.javamysql.model.User;

import java.util.List;

/**
 * Public DAO API methods for  {@link User} class.
 */
public interface IUserDAO {
    User insert(User user) throws DAOException;
    User update(User user) throws DAOException;
    void delete(int userId) throws DAOException;
    List<User> getByUserLastname(String lastname) throws DAOException;
    User getByUserId(int userId) throws DAOException;
}
