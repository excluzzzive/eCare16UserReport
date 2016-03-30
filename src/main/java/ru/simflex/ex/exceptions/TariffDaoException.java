package ru.simflex.ex.exceptions;

/**
 * Custom dao tariff exception.
 */
public class TariffDaoException extends GenericDaoException {
    public TariffDaoException(String message) {
        super(message);
    }

    public TariffDaoException(String message, Exception e) {
        super(message, e);
    }
}
