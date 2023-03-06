package com.manokel.tinysensor.javamysql.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void gettersSetters() {
        User user = new User();
        user.setUserId(1);
        assertEquals(user.getUserId(), 1);

        user.setLastname("Ray");
        assertEquals(user.getLastname(), "Ray");

        user.setFirstname("Man");
        assertEquals(user.getFirstname(), "Man");

        user.setEmail("manray@email.com");
        assertEquals(user.getEmail(), "manray@email.com");

        user.setPostcode("TX1234");
        assertEquals(user.getPostcode(), "TX1234");
    }

    @Test
    void overloadedConstructorAndToString() {
        User user = new User(2, "Dylan", "Bob", "bobdylan@email.com", "TE1234");
        assertEquals(user.getUserId(), 2);
        assertEquals(user.getLastname(), "Dylan");
        assertEquals(user.getFirstname(), "Bob");
        assertEquals(user.getEmail(), "bobdylan@email.com");
        assertEquals(user.getPostcode(), "TE1234");

        String expected = "User{userId='2', lastname='Dylan', firstname='Bob', email='bobdylan@email.com', postcode='TE1234'}";
        assertEquals(expected, user.toString());
    }

}