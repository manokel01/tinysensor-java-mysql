package com.manokel.tinysensor.javamysql.dao;

import com.manokel.tinysensor.javamysql.dao.dbutil.DBHelper;
import com.manokel.tinysensor.javamysql.dao.exceptions.DAOException;
import com.manokel.tinysensor.javamysql.model.User;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImplTest {

    // gets executed only once - to connect to database
    private static IUserDAO userDAO;

    // gets executed before each test - creates dummies
    @BeforeAll
    public static void setupClass() {
        userDAO = new UserDAOImpl();
    }

    @BeforeEach
    void setUp() throws DAOException {
        createDummyUsers();
    }

    @AfterEach
    void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @Test
    void persistAndGetUser() throws DAOException {
        User user = new User();
        user.setLastname("Dylan");
        user.setFirstname("Bob");
        user.setEmail("bobdylan@email.com");
        user.setPostcode("TN1234");
        userDAO.insert(user);

        List<User> users = userDAO.getByUserLastname("Dylan");
        // assertEquals(1, users.size());
        assertTrue(users.size() == 1);
    }

    @Test
    void update() throws DAOException {
        User user = new User();
        user.setUserId(2);
        user.setLastname("Giannotsou2");
        user.setFirstname("Anna2");
        user.setEmail("anna2giannoutsou2@email.com");
        user.setPostcode("AT1234");
        userDAO.insert(user);

        List<User> users = userDAO.getByUserLastname(user.getLastname());

        assertEquals(users.get(0).getLastname(), "Giannotsou2");
    }

    @Test
    void delete() throws DAOException {
        int id = 1;
        userDAO.delete(id);

        User user = userDAO.getByUserId(1);
        assertNull(user);
    }

    @Test
    void getByUserLastname() throws DAOException {
        List<User> users = userDAO.getByUserLastname("Androu");
        assertEquals(1, users.size());
    }

    @Test
    void getByUserId() throws DAOException {
        int id = 2;
        User user = userDAO.getByUserId(id);
        assertEquals("Giannoutsou", user.getLastname());
    }

    public static void createDummyUsers() throws DAOException {
        User user = new User();
        user.setLastname("Androutsos");
        user.setFirstname("Thanassis");
        user.setEmail("ThanassisAndroutsos@email.com");
        user.setPostcode("TX1234");
        userDAO.insert(user);

        user = new User();
        user.setLastname("Giannoutsou");
        user.setFirstname("Anna");
        user.setEmail("AnnaGiannoutsou@email.com");
        user.setPostcode("EU1234");
        userDAO.insert(user);

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