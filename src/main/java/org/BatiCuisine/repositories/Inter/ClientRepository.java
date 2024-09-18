package org.BatiCuisine.repositories.Inter;

import org.BatiCuisine.models.entities.Client;

import java.sql.SQLException;

public interface ClientRepository {
    void addClient(Client client) throws SQLException;
}
