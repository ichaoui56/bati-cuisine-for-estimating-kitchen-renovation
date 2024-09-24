package org.BatiCuisine.services.Inter;

import org.BatiCuisine.models.entities.Client;

import java.sql.SQLException;
import java.util.Map;

public interface ClientService {
    Client ajouterClient(Client client) throws SQLException;
    Map<Integer, Client> searchClientByName(String name) throws SQLException;
}
