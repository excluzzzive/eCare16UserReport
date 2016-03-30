package ru.simflex.ex.managedbeans;

import org.omnifaces.util.Faces;
import ru.simflex.ex.entities.WSTariff;
import ru.simflex.ex.services.interfaces.TariffService;
import ru.simflex.ex.services.interfaces.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Managed bean for representing choose tariff page.
 */
@ManagedBean
public class UserReport {

    /**
     * Selected tariff Id. Need for a report.
     */
    private int selectedTariffId;

    /**
     * TariffService object.
     */
    @EJB
    private TariffService tariffService;

    /**
     * UserService object.
     */
    @EJB
    private UserService userService;

    /**
     * Default constructor.
     */
    public UserReport() {
    }

    /**
     * Standard POJO getter.
     */
    public int getSelectedTariffId() {
        return selectedTariffId;
    }

    /**
     * Standard POJO setter.
     */
    public void setSelectedTariffId(int selectedTariffId) {
        this.selectedTariffId = selectedTariffId;
    }

    /**
     * Method returns list of tariffs via tariff service methods.
     * @return List of WSTariff objects
     */
    public List<WSTariff> getTariffList() {
        return tariffService.getTariffList();
    }

    /**
     * Generates user report by a selected tariff id.
     */
    public void generateUserReport() throws IOException {
        String pathToFile = userService.generateUserReportById(selectedTariffId);
        File file = new File(pathToFile);
        Faces.sendFile(file, true);
    }



}
