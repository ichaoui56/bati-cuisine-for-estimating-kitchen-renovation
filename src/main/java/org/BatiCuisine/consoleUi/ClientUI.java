package org.BatiCuisine.consoleUi;

import org.BatiCuisine.models.entities.Client;
import org.BatiCuisine.services.Inter.ClientService;
import org.BatiCuisine.utils.ValidatorUtils;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class ClientUI {
    private final ClientService clientService;
    private final ProjetUI projetUI;
    private final Scanner scanner = new Scanner(System.in);

    ClientUI(ClientService clientService, ProjetUI projetUI) {
        this.clientService = clientService;
        this.projetUI = projetUI;
    }

    public void createProject(PrincipalUI principalUI) throws SQLException {
        int choix = 0;

        do {
            System.out.println("\n");
            System.out.println("**=================================|(    \u001B[32m📃   Menu client   📃\u001B[0m    )|=================================**");
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
                continue; // Prompt is displayed again
            }

            switch (choix) {
                case 1:
                    searchClient();
                    break;
                case 2:
                    addNewClient();
                    break;
                case 3:
                    principalUI.displayMenu();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer."); // Display prompt again
            }
        } while (choix != 3);
    }

    public void addNewClient() throws SQLException {
        System.out.println("**===================================|(    \u001B[32m📃   Add Client   📃\u001B[0m    )|=================================**");
        System.out.println("||                                                                                                     ||");

        String nom = ValidatorUtils.validName("||                                       Entrez le nom du client : ");
        String address = ValidatorUtils.validString("||                                       Entrez l'adresse du client : ");
        String phoneNumber = ValidatorUtils.validPN("||                                  Entrez le numéro de téléphone du client : ");
        boolean estProfessionnal = ValidatorUtils.validBoolean("||                               Le client est-il professionnel (oui/non) : ");

        System.out.println("**====================================================================================================**");

        Client client = new Client(nom, address, phoneNumber, estProfessionnal);
        Client createdClient = clientService.ajouterClient(client);

        if (createdClient.getId() > 0) {
            System.out.println("\n");
            System.out.println("                                     \u001B[32m ✅Client ajouté avec succès ✅\u001B[0m ");
            System.out.println("\n");
            projetUI.addProjet(createdClient);
        } else {
            System.out.println("                                      ❌Échec de l'ajout du client❌");
        }
    }

    public void searchClient() throws SQLException {
        boolean continueSearching;

        do {
            continueSearching = false;
            String name = ValidatorUtils.validString("||                                       Entrez le nom du client à chercher : ");
            Map<Integer, Client> clients = clientService.searchClientByName(name);

            if (clients.isEmpty()) {
                System.out.println("                                Aucun client trouvé avec le nom : " + name);
            } else if (clients.size() > 1) {
                selectClient(clients);
            } else {
                Client client = clients.values().iterator().next();
                System.out.println("\n");
                System.out.println("        **=========================|(\u001B[32m📃   Résultats de la recherche   📃\u001B[0m)|=======================**");
                System.out.println("        ||                                                                                       ||");
                System.out.println("               ID : " + client.getId()
                        + " | Nom : " + client.getNom()
                        + " | Adresse : " + client.getAddress()
                        + " | Téléphone : " + client.getPhoneNumber());
                System.out.println("        ||                                                                                       ||");
                System.out.println("        **=======================================================================================**");

                System.out.print("                            Souhaitez-vous continuer avec ce client ? (oui/non) : ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("oui")) {
                    projetUI.addProjet(client);
                } else {
                    continueSearching = true; // Continue searching for another client
                }
            }
        } while (continueSearching);
    }

    private void selectClient(Map<Integer, Client> clients) throws SQLException {
        System.out.print("                  Veuillez entrer l'ID du client pour continuer à ajouter un projet : ");
        String input = scanner.nextLine().trim();
        System.out.println("\n");
        try {
            int clientId = Integer.parseInt(input);
            if (clients.containsKey(clientId)) {
                Client selectedClient = clients.get(clientId);
                projetUI.addProjet(selectedClient);
            } else {
                System.out.println("                                ID de client invalide. Veuillez réessayer.");
                selectClient(clients); // Re-prompt for client ID
            }
        } catch (NumberFormatException e) {
            System.out.println("                                        Veuillez entrer un ID valide.");
            selectClient(clients); // Re-prompt for client ID
        }
    }
}
