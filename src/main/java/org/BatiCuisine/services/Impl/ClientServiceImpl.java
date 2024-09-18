package org.BatiCuisine.services.Impl;

import org.BatiCuisine.models.entities.Client;
import org.BatiCuisine.repositories.Inter.ClientRepository;
import org.BatiCuisine.services.Inter.ClientService;

import java.sql.SQLException;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean ajouterClient(Client client) {
        try {
            clientRepository.addClient(client);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
