package com.manokel.tinysensor.javamysql.service;

        import com.manokel.tinysensor.javamysql.dao.exceptions.DAOException;
        import com.manokel.tinysensor.javamysql.dto.UserDTO;
        import com.manokel.tinysensor.javamysql.model.User;
        import com.manokel.tinysensor.javamysql.service.exceptions.UserNotFoundException;

        import java.util.List;

public interface IUserService {
    User insertUser(UserDTO userToInsert) throws DAOException;
    User updateUser(UserDTO userToUpdate) throws DAOException, UserNotFoundException;
    void deleteUser(int userId) throws DAOException, UserNotFoundException;
    List<User> getUsersByLastname(String lastname) throws DAOException;
}
