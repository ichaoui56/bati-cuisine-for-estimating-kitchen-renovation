package org.BatiCuisine.consoleUi;

import org.BatiCuisine.services.Inter.ClientService;

import java.sql.SQLException;
import java.util.Scanner;

public class PrincipalUI {
    private final Scanner scanner = new Scanner(System.in);
    private final ClientUI clientUI;

    PrincipalUI(ClientUI clientUI) {
        this.clientUI = clientUI;
    }

    public void displayMenu() throws SQLException {
        int choix = 0;

        do {
            System.out.println("\n\n");
            System.out.println("**===============================|(    📃   Menu Principal   📃    )|================================**");
            System.out.println("||                                                                                                    ||");
            System.out.println("||                                   1. Créer un nouveau projet                                       ||");
            System.out.println("||                                   2. Afficher les projets existants                                ||");
            System.out.println("||                                   3. Calculer le coût d'un projet                                  ||");
            System.out.println("||                                   4. Quitter                                                       ||");
            System.out.println("||                                                                                                    ||");
            System.out.println("**====================================================================================================**");
            System.out.print("                                         Entrez votre choix : ");

            // Read and validate menu choice
            String input = scanner.nextLine().trim();
            try {
                choix = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(" Choix invalide. Veuillez réessayer.");
                continue;
            }

            switch (choix) {
                case 1:
                    clientUI.createProject();
                    break;
                case 2:
                    System.out.println("Affichage des projets existants...");
                    break;
                case 3:
                    System.out.println("Calcul du coût d'un projet...");
                    break;
                case 4:
                    System.out.println("Quitter l'application...");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }

        } while (choix != 4);
    }


}
