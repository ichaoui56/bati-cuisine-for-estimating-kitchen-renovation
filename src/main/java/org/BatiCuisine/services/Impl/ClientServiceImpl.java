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

    public int ajouterClient(Client client) throws SQLException {
       return clientRepository.addClient(client);
    }
}
