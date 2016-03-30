package ru.simflex.ex.services.interfaces;

import ru.simflex.ex.entities.WSTariff;

import java.util.List;

/**
 * Tariff service interface.
 */
public interface TariffService {

    /**
     * Method to get a list of Tariff objects from main app.
     * @return List of WSTariff objects
     */
    List<WSTariff> getTariffList();
}
