package org.BatiCuisine.consoleUi;

import org.BatiCuisine.models.entities.Devis;
import org.BatiCuisine.repositories.Impl.ClientRepositoryImpl;
import org.BatiCuisine.repositories.Impl.MainOeuvreRepositoryImpl;
import org.BatiCuisine.repositories.Impl.MaterialRepositoryImpl;
import org.BatiCuisine.repositories.Impl.ProjetRepositoryImpl;
import org.BatiCuisine.repositories.Inter.ClientRepository;
import org.BatiCuisine.repositories.Inter.MainOeuvreRepository;
import org.BatiCuisine.repositories.Inter.MaterialRepository;
import org.BatiCuisine.repositories.Inter.ProjetRepository;
import org.BatiCuisine.services.Impl.ClientServiceImpl;
import org.BatiCuisine.services.Impl.MainOeuvreServiceImpl;
import org.BatiCuisine.services.Impl.MaterialServiceImpl;
import org.BatiCuisine.services.Impl.ProjetServiceImpl;
import org.BatiCuisine.services.Inter.ClientService;
import org.BatiCuisine.services.Inter.MainOeuvreService;
import org.BatiCuisine.services.Inter.MaterialService;
import org.BatiCuisine.services.Inter.ProjetService;

import java.sql.SQLException;

public class MainUI {
    private final PrincipalUI principalUI;

    public MainUI() throws SQLException {
        ClientRepository clientRepository = new ClientRepositoryImpl();
        ClientService clientService = new ClientServiceImpl(clientRepository);
        ProjetRepository projetRepository = new ProjetRepositoryImpl();
        ProjetService projetService = new ProjetServiceImpl(projetRepository);
        MaterialRepository materialRepository = new MaterialRepositoryImpl();
        MaterialService materialService = new MaterialServiceImpl(materialRepository);
        MainOeuvreRepository mainOeuvreRepository = new MainOeuvreRepositoryImpl();
        MainOeuvreService mainOeuvreService = new MainOeuvreServiceImpl(mainOeuvreRepository);
        ComposantUI composantUI = new ComposantUI(materialService, mainOeuvreService);
        DevisUI devisUI = new DevisUI();
        ProjetUI projetUI = new ProjetUI(projetService, composantUI, devisUI);
        ClientUI clientUI = new ClientUI(clientService, projetUI);
        principalUI = new PrincipalUI(clientUI);
    }

    public void run() throws SQLException {
        principalUI.displayMenu();
    }

}
