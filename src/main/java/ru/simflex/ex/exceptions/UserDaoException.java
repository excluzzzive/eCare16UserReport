package ru.simflex.ex.exceptions;

/**
 * Custom user dao exception.
 */
public class UserDaoException extends GenericDaoException {
    public UserDaoException(String message) {
        super(message);
    }

    public UserDaoException(String message, Exception e) {
        super(message, e);
    }
}
