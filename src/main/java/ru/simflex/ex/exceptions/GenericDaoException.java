package ru.simflex.ex.exceptions;

/**
 * Generic DAO exception.
 */
public class GenericDaoException extends Exception {

    public GenericDaoException(String message) {
        super(message);
    }

    public GenericDaoException(String message, Exception e) {
        super(message, e);
    }
}
