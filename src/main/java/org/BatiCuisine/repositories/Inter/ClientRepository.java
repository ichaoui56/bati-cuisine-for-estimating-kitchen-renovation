package org.BatiCuisine.repositories.Inter;

import org.BatiCuisine.models.entities.Client;

import java.sql.SQLException;

public interface ClientRepository {
    Client addClient(Client client) throws SQLException;
}
