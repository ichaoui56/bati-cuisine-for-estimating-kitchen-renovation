package org.BatiCuisine.repositories.Inter;

import org.BatiCuisine.models.entities.Client;

import java.sql.SQLException;

public interface ClientRepository {
    int addClient(Client client) throws SQLException;
}
