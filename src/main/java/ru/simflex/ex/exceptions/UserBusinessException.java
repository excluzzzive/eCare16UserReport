package ru.simflex.ex.exceptions;

/**
 * Created by User on 30.03.2016.
 */
public class UserBusinessException extends GenericBusinessException {

    public UserBusinessException(String message) {
        super(message);
    }

    public UserBusinessException(String message, Exception e) {
        super(message, e);
    }
}
