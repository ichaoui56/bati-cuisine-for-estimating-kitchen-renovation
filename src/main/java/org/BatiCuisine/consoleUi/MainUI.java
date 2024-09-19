package org.BatiCuisine.consoleUi;

import org.BatiCuisine.repositories.Impl.ClientRepositoryImpl;
import org.BatiCuisine.repositories.Inter.ClientRepository;
import org.BatiCuisine.services.Impl.ClientServiceImpl;
import org.BatiCuisine.services.Inter.ClientService;

import java.sql.SQLException;

public class MainUI {
    private final PrincipalUI principalUI;

    public MainUI() throws SQLException {
        ClientRepository clientRepository = new ClientRepositoryImpl();
        ClientService clientService = new ClientServiceImpl(clientRepository);
        ProjetUI projetUI = new ProjetUI();
        ClientUI clientUI = new ClientUI(clientService, projetUI);
        principalUI = new PrincipalUI(clientUI);
    }

    public void run() throws SQLException {
        principalUI.displayMenu();
    }

}
