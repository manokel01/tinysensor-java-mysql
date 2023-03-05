package com.manokel.tinysensor.javamysql.dao;

import com.manokel.tinysensor.javamysql.dao.exceptions.DAOException;
import com.manokel.tinysensor.javamysql.model.User;
import com.manokel.tinysensor.javamysql.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    @Override
    public User insert(User user) throws DAOException {
        String sql = "INSERT INTO USERS (LASTNAME, FIRSTNAME, EMAIL, POSTCODE)" +
                "VALUES (?, ?, ?, ?)";
        // close with resources to close connection and prepared statement.
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            String lastname = user.getLastname();
            String firstname = user.getFirstname();
            String email = user.getEmail();
            String postcode = user.getPostcode();

            if ((firstname.equals("")) || (lastname.equals(""))
                || (email.equals("")) || (postcode.equals(""))) {
                System.err.println("Please enter all fields.");
                return null;
            }

            p.setString(1, lastname);
            p.setString(2, firstname);
            p.setString(3, email);
            p.setString(4, postcode);
            p.executeUpdate();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("SQL Error in User" + user + "insertion.");
        }
    }

    @Override
    public User update(User user) throws DAOException {
        // search with unique key (surrogate opr natural key)
        String sql = "UPDATE USERS SET LASTNAME = ?, FIRSTNAME = ?," +
                "EMAIL = ?, POSTCODE = ? WHERE USERID = ?";
        // close with resources to close connection and prepared statement.
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            int userId = user.getUserId();
            String lastname = user.getLastname();
            String firstname = user.getFirstname();
            String email = user.getEmail();
            String postcode = user.getPostcode();

            if ((firstname.equals("")) || (lastname.equals(""))
                    || (email.equals("")) || (postcode.equals(""))) {
                System.err.println("Please enter all fields.");
                return null;
            }

            p.setString(1, lastname);
            p.setString(2, firstname);
            p.setString(3, email);
            p.setString(4, postcode);
            p.setInt(5, userId);
            p.executeUpdate();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("SQL Error in User "+ user + "update.");
        }
    }

    @Override
    public void delete(int userId) throws DAOException {
        // search with unique key (surrogate opr natural key)
        String sql = "DELETE FROM USERS WHERE USERID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setInt(1, userId);
            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("SQL Error in User with id" + userId + "deletion.");
        }
    }

    @Override
    public List<User> getByUserLastname(String lastname) throws DAOException {
        String sql = "SELECT USERID, LASTNAME, FIRSTNAME, EMAIL, POSTCODE FROM USERS WHERE LASTNAME LIKE ?";
        ResultSet rs;
        List<User> users = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setString(1, lastname + '%');
            rs = p.executeQuery();

            while (rs.next()) {
                User user = new User(
                            rs.getInt("USERID"),
                            rs.getString("LASTNAME"),
                            rs.getString("FIRSTNAME"),
                            rs.getString("EMAIL"),
                            rs.getString("POSTCODE")
                );
                // add to the arraylist
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("SQL Error in User with lastname = " + lastname);
        }
    }

    @Override
    public User getByUserId(int userId) throws DAOException {
        User user = null;
        ResultSet rs;
        String sql = "SELECT * FROM USERS WHERE USERID = ?";

        try  (Connection conn = DBUtil.getConnection();
              PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, userId);
            rs = p.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("USERID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("POSTCODE")
                );
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("SQL Error in Users with id = " + userId);
        }
    }
}
