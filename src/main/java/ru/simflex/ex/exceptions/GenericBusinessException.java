package ru.simflex.ex.exceptions;

/**
 * Generic business exception.
 */
public class GenericBusinessException extends RuntimeException {

    public GenericBusinessException(String message) {
        super(message);
    }

    public GenericBusinessException(String message, Exception e) {
        super(message, e);
    }
}
