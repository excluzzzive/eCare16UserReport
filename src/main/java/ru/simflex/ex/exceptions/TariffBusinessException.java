package ru.simflex.ex.exceptions;

/**
 * Created by User on 30.03.2016.
 */
public class TariffBusinessException extends GenericBusinessException {

    public TariffBusinessException(String message) {
        super(message);
    }

    public TariffBusinessException(String message, Exception e) {
        super(message, e);
    }
}
