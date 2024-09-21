package org.BatiCuisine.consoleUi;

import org.BatiCuisine.models.entities.Client;
import org.BatiCuisine.services.Inter.ClientService;

import java.sql.SQLException;
import java.util.Scanner;

public class ClientUI {
    private final ClientService clientService;
    private final ProjetUI projetUI;
    private final Scanner scanner = new Scanner(System.in);

    ClientUI(ClientService clientService, ProjetUI projetUI) {
        this.clientService = clientService;
        this.projetUI = projetUI;
    }

    public void createProject() throws SQLException {
        int choix = 0;
    
        do {
            System.out.println("\n");
            System.out.println("**=================================|(    📃   Menu Client   📃    )|=================================**");
            System.out.println("||                                                                                                    ||");
            System.out.println("||                                    1. Chercher un client existant                                  ||");
            System.out.println("||                                    2. Ajouter un nouveau client                                    ||");
            System.out.println("||                                    3. Quitter                                                      ||");
            System.out.println("||                                                                                                    ||");
            System.out.println("**====================================================================================================**");
            System.out.print("                                           Entrez votre choix : ");
            String input = scanner.nextLine().trim();
            System.out.println("\n");

            try {
                choix = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("                                 Choix invalide. Veuillez réessayer.");
                continue;
            }

            switch (choix) {
                case 1:

                    System.out.println("Chercher un client existant...");
                    break;
                case 2:
                    addNewClient();
                    break;
                case 3:
                    System.out.println("Quitter l'application...");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 3);
    }

    public void addNewClient() throws SQLException {
        System.out.println("**=================================|(    📃   Add Client   📃    )|=================================**");
        System.out.println("||                                                                                                   ||");
        System.out.print("||                                       Entrez le nom du client :");
        String nom = scanner.nextLine().trim();


        System.out.print("||                                       Entrez l'adresse du client :");
        String address = scanner.nextLine().trim();


        System.out.print("||                                  Entrez le numéro de téléphone du client :");
        String phoneNumber = scanner.nextLine().trim();

        System.out.print("||                               Le client est-il professionnel (true/false) : ");
        boolean estProfessionnal = Boolean.parseBoolean(scanner.nextLine().trim());
        System.out.println("**====================================================================================================**");


        Client client = new Client(nom, address, phoneNumber, estProfessionnal);
        Client createdClient = clientService.ajouterClient(client);

        if (createdClient.getId() > 0) {
            System.out.println("\n");
            System.out.println("                                       Client ajouté avec succès !");
            System.out.println("\n");
            projetUI.addProjet(createdClient);

        } else {
            System.out.println("                                      Échec de l'ajout du client.");
        }

    }

}
