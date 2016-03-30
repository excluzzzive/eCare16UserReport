package ru.simflex.ex.services.implementation;

import ru.simflex.ex.dao.interfaces.TariffDao;
import ru.simflex.ex.entities.WSTariff;
import ru.simflex.ex.exceptions.GenericDaoException;
import ru.simflex.ex.exceptions.TariffBusinessException;
import ru.simflex.ex.services.interfaces.TariffService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Tariff service implementation.
 */
@Stateless
public class TariffServiceImplementation implements TariffService {

    /**
     * TariffDao instance.
     */
    @EJB
    private TariffDao tariffDao;

    /**
     * {@inheritDoc}
     */
    public List<WSTariff> getTariffList() {
        try {
            return tariffDao.getTariffList();
        } catch (GenericDaoException e) {
            throw new TariffBusinessException("Something gone wrong!", e);
        }
    }
}
