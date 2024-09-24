package org.BatiCuisine.consoleUi;

import org.BatiCuisine.models.entities.Devis;
import org.BatiCuisine.repositories.Impl.*;
import org.BatiCuisine.repositories.Inter.*;
import org.BatiCuisine.services.Impl.*;
import org.BatiCuisine.services.Inter.*;

import java.sql.SQLException;

public class MainUI {
    private final PrincipalUI principalUI;

    public MainUI() throws SQLException {
        ClientRepository clientRepository = new ClientRepositoryImpl();
        ClientService clientService = new ClientServiceImpl(clientRepository);

        ProjetRepository projetRepository = new ProjetRepositoryImpl(clientRepository);
        ProjetService projetService = new ProjetServiceImpl(projetRepository);

        MaterialRepository materialRepository = new MaterialRepositoryImpl();
        MaterialService materialService = new MaterialServiceImpl(materialRepository);

        MainOeuvreRepository mainOeuvreRepository = new MainOeuvreRepositoryImpl();
        MainOeuvreService mainOeuvreService = new MainOeuvreServiceImpl(mainOeuvreRepository);

        DevisRepository devisRepository = new DevisRepositoryImpl();
        DevisService devisService = new DevisServiceImpl(devisRepository);

        ComposantUI composantUI = new ComposantUI(materialService, mainOeuvreService);
        DevisUI devisUI = new DevisUI(devisService, projetService);
        ProjetUI projetUI = new ProjetUI(projetService, composantUI, devisUI);
        ClientUI clientUI = new ClientUI(clientService, projetUI);
        principalUI = new PrincipalUI(clientUI,projetUI);
    }

    public void run() throws SQLException {
        principalUI.displayMenu();
    }

}
