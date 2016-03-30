package ru.simflex.ex.dao.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import ru.simflex.ex.dao.interfaces.UserDao;
import ru.simflex.ex.entities.WSUser;
import ru.simflex.ex.exceptions.UserDaoException;

import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * UserDao implementation.
 */
@Stateless
public class UserDaoImplementation implements UserDao {

    /**
     * Rest Client.
     */
    private Client client = Client.create();

    /**
     * {@inheritDoc}
     */
    public List<WSUser> getUserListByTariffId(int id) throws UserDaoException {

        String url = String.format("http://localhost:8081/webservice/user/list/%d", id);

        WebResource webResource = client.resource(url);
        ObjectMapper mapper = new ObjectMapper();
        ClientResponse response = webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new UserDaoException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String output = response.getEntity(String.class);

        List<WSUser> wsUserList;
        try {
            wsUserList = mapper.readValue(output, new TypeReference<List<WSUser>>(){});
        } catch (Exception e) {
            throw new UserDaoException("Error while converting object!");
        }

        return wsUserList;

    }
}
