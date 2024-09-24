package org.BatiCuisine.services.Impl;

import org.BatiCuisine.models.entities.Client;
import org.BatiCuisine.repositories.Inter.ClientRepository;
import org.BatiCuisine.services.Inter.ClientService;

import java.sql.SQLException;
import java.util.Map;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client ajouterClient(Client client) throws SQLException {
       return clientRepository.addClient(client);
    }

    public Map<Integer, Client> searchClientByName(String name) throws SQLException {
        return clientRepository.searchClientByName(name);
    }
}
