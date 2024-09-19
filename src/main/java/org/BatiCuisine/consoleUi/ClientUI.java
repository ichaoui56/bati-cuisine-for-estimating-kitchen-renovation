package org.BatiCuisine.consoleUi;

import org.BatiCuisine.models.entities.Client;
import org.BatiCuisine.services.Inter.ClientService;

import java.sql.SQLException;
import java.util.Scanner;

public class ClientUI {
    private final ClientService clientService;
    private final Scanner scanner = new Scanner(System.in);

    ClientUI(ClientService clientService) {
        this.clientService = clientService;
    }

    public void createProject() throws SQLException {
        int choix = 0;
    
        do {
            System.out.println("\n\n");
            System.out.println("**=================================|(    📃   Menu Client   📃    )|=================================**");
            System.out.println("||                                                                                                        ||");
            System.out.println("||                                    1. Chercher un client existant                                      ||");
            System.out.println("||                                    2. Ajouter un nouveau client                                        ||");
            System.out.println("||                                    3. Quitter                                                          ||");
            System.out.println("||                                                                                                        ||");
            System.out.println("**======================================================================================================**");
            System.out.print("\n                                          Entrez votre choix : ");

            String input = scanner.nextLine().trim();
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
        System.out.print("                                              Entrez le nom du client : ");
        String nom = scanner.nextLine().trim();
        if (nom.isEmpty()) {
            System.out.println("                                        Le nom du client ne peut pas être vide.");
            return;
        }

        // Read and validate address
        System.out.print("                                              Entrez l'adresse du client : ");
        String address = scanner.nextLine().trim();
        if (address.isEmpty()) {
            System.out.println("                                        L'adresse du client ne peut pas être vide.");
            return;
        }

        // Read and validate phone number
        System.out.print("                                              Entrez le numéro de téléphone du client : ");
        String phoneNumber = scanner.nextLine().trim();
        if (phoneNumber.isEmpty()) {
            System.out.println("Le numéro de téléphone du client ne peut pas être vide.");
            return;
        }

        boolean estProfessionnal = false;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Le client est-il professionnel (true/false) : ");
            if (scanner.hasNextBoolean()) {
                estProfessionnal = scanner.nextBoolean();
                validInput = true;
            } else {
                System.out.println("Entrée invalide pour le boolean. Veuillez entrer 'true' ou 'false'.");
                scanner.next();
            }
        }
        scanner.nextLine();

        Client client = new Client(nom, address, phoneNumber, estProfessionnal);
        boolean isAdded = clientService.ajouterClient(client);

        if (isAdded) {
            System.out.println("Client ajouté avec succès !");
        } else {
            System.out.println("Échec de l'ajout du client.");
        }
    }

}
