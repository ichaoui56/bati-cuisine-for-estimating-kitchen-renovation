package org.BatiCuisine.consoleUi;

import org.BatiCuisine.repositories.Impl.ClientRepositoryImpl;
import org.BatiCuisine.repositories.Inter.ClientRepository;
import org.BatiCuisine.services.Impl.ClientServiceImpl;
import org.BatiCuisine.services.Inter.ClientService;

import java.sql.SQLException;

public class MainUI {
    private final ClientUI clientUI;

    public MainUI() throws SQLException {
        ClientRepository clientRepository = new ClientRepositoryImpl();
        ClientService clientService = new ClientServiceImpl(clientRepository);

        clientUI = new ClientUI(clientService);
    }

    public void run() throws SQLException {
        clientUI.displayMenu();
    }

}
