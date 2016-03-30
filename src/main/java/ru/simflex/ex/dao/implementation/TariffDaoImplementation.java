package ru.simflex.ex.dao.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import ru.simflex.ex.dao.interfaces.TariffDao;
import ru.simflex.ex.entities.WSTariff;
import ru.simflex.ex.exceptions.TariffDaoException;

import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * TariffDao implementation.
 */
@Stateless
public class TariffDaoImplementation implements TariffDao {

    /**
     * Rest Client.
     */
    private Client client = Client.create();

    /**
     * {@inheritDoc}
     */
    public List<WSTariff> getTariffList() throws TariffDaoException {
        WebResource webResource = client.resource("http://localhost:8081/webservice/tariff/list");
        ObjectMapper mapper = new ObjectMapper();
        ClientResponse response = webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new TariffDaoException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String output = response.getEntity(String.class);
        List<WSTariff> wsTariffList;
        try {
            wsTariffList = mapper.readValue(output, new TypeReference<List<WSTariff>>(){});
        } catch (Exception e) {
            throw new TariffDaoException("Error while converting object!");
        }

        return wsTariffList;
    }
}
