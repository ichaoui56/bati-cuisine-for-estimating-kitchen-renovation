package org.BatiCuisine.repositories.Inter;

import org.BatiCuisine.models.entities.Client;

import java.sql.SQLException;
import java.util.Map;

public interface ClientRepository {
    Client addClient(Client client) throws SQLException;
    Client getClientById(int id) throws SQLException;
    Map<Integer, Client> searchClientByName(String name) throws SQLException;
}
