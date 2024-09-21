package org.BatiCuisine.services.Inter;

import org.BatiCuisine.models.entities.Client;

import java.sql.SQLException;

public interface ClientService {
    Client ajouterClient(Client client) throws SQLException;
}
