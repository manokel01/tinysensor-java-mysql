package com.manokel.tinysensor.javamysql.service;

import com.manokel.tinysensor.javamysql.dao.IUserDAO;
import com.manokel.tinysensor.javamysql.dao.UserDAOImpl;
import com.manokel.tinysensor.javamysql.dao.dbutil.DBHelper;
import com.manokel.tinysensor.javamysql.dao.exceptions.DAOException;
import com.manokel.tinysensor.javamysql.dto.UserDTO;
import com.manokel.tinysensor.javamysql.model.User;
import com.manokel.tinysensor.javamysql.service.exceptions.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private static IUserDAO userDAO = new UserDAOImpl();
    private static IUserService userService;

    @BeforeAll
    public static void setupClass() {
        userService = new UserServiceImpl(userDAO);
    }

    @BeforeEach
    public void setup() throws DAOException {
        createDummyUsers();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @Test
    public void insertAndGetUser() throws DAOException {
        UserDTO userDTO = new UserDTO(1, "Giannoutsou", "Anna", "AnnaGiannoutsou@email.com", "EU1234");
        userService.insertUser(userDTO);

        List<User> users = userService.getUsersByLastname(userDTO.getLastname());

        assertEquals(1, users.size());
        assertEquals(users.get(0).getLastname(), userDTO.getLastname());
        assertEquals(users.get(0).getFirstname(), userDTO.getFirstname());
        assertEquals(users.get(0).getEmail(), userDTO.getEmail());
        assertEquals(users.get(0).getPostcode(), userDTO.getPostcode());
    }

    @Test
    public void updateUser() throws DAOException, UserNotFoundException {
        UserDTO userDTO = new UserDTO(1, "Androutsos", "Thanassis", "ThanassisAndroutsos@email.com", "TX1234");
        userService.updateUser((userDTO));

        List<User> users =  userService.getUsersByLastname(userDTO.getLastname());
        assertEquals(users.get(0).getLastname(), userDTO.getLastname());
        assertEquals(users.get(0).getFirstname(), userDTO.getFirstname());
        assertEquals(users.get(0).getEmail(), userDTO.getEmail());
        assertEquals(users.get(0).getPostcode(), userDTO.getPostcode());
    }

    // test the UserNotFoundException
    @Test
    public void updateInvalidUser() throws DAOException, UserNotFoundException {
        UserDTO userDTO = new UserDTO(10, "Androutsos", "Thanassis", "ThanassisAndroutsos@email.com", "TX1234");

        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(userDTO);
        });
    }

    @Test
    public void deleteUser() throws DAOException, UserNotFoundException {
        UserDTO userDTO = new UserDTO(1, "Androutsos", "Thanassis", "ThanassisAndroutsos@email.com", "TX1234");
        userService.deleteUser(userDTO.getUserId());

        List<User> users = userService.getUsersByLastname(userDTO.getLastname());
        assertEquals(0, users.size());
    }

    @Test
    public void InvalidUser() throws DAOException, UserNotFoundException {
        UserDTO userDTO = new UserDTO(10, "Androutsos", "Thanassis", "ThanassisAndroutsos@email.com", "TX1234");

        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUser(userDTO.getUserId());
        });
    }

    public static void createDummyUsers() throws DAOException {
        User user = new User();
        user.setLastname("Androutsos");
        user.setFirstname("Thanassis");
        user.setEmail("ThanassisAndroutsos@email.com");
        user.setPostcode("TX1234");
        userDAO.insert(user);

//        is created at insertAndGetuser() method
//        user = new User();
//        user.setLastname("Giannoutsou");
//        user.setFirstname("Anna");
//        user.setEmail("AnnaGiannoutsou@email.com");
//        user.setPostcode("EU1234");
//        userDAO.insert(user);

        user = new User();
        user.setLastname("W.");
        user.setFirstname("Alice");
        user.setEmail("AliceW@email.com");
        user.setPostcode("LN1234");
        userDAO.insert(user);

        user = new User();
        user.setLastname("Lennon");
        user.setFirstname("John");
        user.setEmail("JohnLennon@email.com");
        user.setPostcode("CA1234");
        userDAO.insert(user);
    }
}