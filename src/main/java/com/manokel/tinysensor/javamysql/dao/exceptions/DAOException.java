package com.manokel.tinysensor.javamysql.dao.exceptions;

/**
 * Exception class for the DAO layer to
 * pass on relevant messages that take place during
 * CRUD queries.
 * Part of tinysensor project.
 *
 * @author manokel01
 */
public class DAOException extends Exception {
    private final static long serialVersionUID = 1L;

    public DAOException(String s) {
        super(s);
    }
}
