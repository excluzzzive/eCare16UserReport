package ru.simflex.ex.dao.interfaces;

import ru.simflex.ex.entities.WSTariff;
import ru.simflex.ex.exceptions.GenericDaoException;

import java.util.List;

/**
 * TariffDao interface.
 */
public interface TariffDao {

    /**
     * Method to get a list of Tariff objects from main app.
     *
     * @return List of WSTariff objects
     */
    List<WSTariff> getTariffList() throws GenericDaoException;
}
