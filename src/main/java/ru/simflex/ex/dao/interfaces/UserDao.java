package ru.simflex.ex.dao.interfaces;

import ru.simflex.ex.entities.WSUser;
import ru.simflex.ex.exceptions.GenericDaoException;

import java.util.List;

/**
 * UserDao interface.
 */
public interface UserDao {

    /**
     * Method returns list of WSUser object for next converting to PDF report.
     *
     * @param id Id of tariff
     * @return List of WSUser objects
     */
    List<WSUser> getUserListByTariffId(int id) throws GenericDaoException;
}
