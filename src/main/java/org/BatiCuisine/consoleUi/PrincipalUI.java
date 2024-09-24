package org.BatiCuisine.consoleUi;

import org.BatiCuisine.models.entities.Projet;
import org.BatiCuisine.services.Inter.ClientService;

import java.sql.SQLException;
import java.util.Scanner;

public class PrincipalUI {
    private final Scanner scanner = new Scanner(System.in);
    private final ClientUI clientUI;
    private final ProjetUI projetUI;

    PrincipalUI(ClientUI clientUI, ProjetUI projetUI) {
        this.clientUI = clientUI;
        this.projetUI = projetUI;
    }

    public void displayMenu() throws SQLException {
        int choix = 0;
        System.out.println("\n\n");
        System.out.println(applicationTitle());
        do {
            System.out.println("**===============================|(    \u001B[36m📃   Menu Principal   📃\u001B[0m    )|================================**");
            System.out.println("||                                                                                                    ||");
            System.out.println("||                                   1. Créer un nouveau projet                                       ||");
            System.out.println("||                                   2. Afficher les projets existants                                ||");
            System.out.println("||                                   3. Calculer le coût d'un projet                                  ||");
            System.out.println("||                                   4. Quitter                                                       ||");
            System.out.println("||                                                                                                    ||");
            System.out.println("**====================================================================================================**");
            System.out.print("                                         Entrez votre choix : ");

            String input = scanner.nextLine().trim();
            try {
                choix = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\n");
                System.out.println("Choix invalide. Veuillez réessayer.");
                continue;
            }

            switch (choix) {
                case 1:
                    clientUI.createProject(this);
                    break;
                case 2:
                    projetUI.displayAllProjects();;
                    break;
                case 3:
                    projetUI.displayProjets(this);
                    break;
                case 4:
                    System.out.println("\n");
                    System.out.println("                                          Quitter l'application...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }

        } while (choix != 4);
    }


    private String applicationTitle() {
        return "\u001B[32m" +
                "            ██████╗  █████╗ ████████╗██╗       ██████╗██╗   ██╗██╗███████╗██╗███╗   ██╗███████╗\n" +
                "            ██╔══██╗██╔══██╗╚══██╔══╝██║      ██╔════╝██║   ██║██║██╔════╝██║████╗  ██║██╔════╝\n" +
                "            ██████╔╝███████║   ██║   ██║█████╗██║     ██║   ██║██║███████╗██║██╔██╗ ██║█████╗  \n" +
                "            ██╔══██╗██╔══██║   ██║   ██║╚════╝██║     ██║   ██║██║╚════██║██║██║╚██╗██║██╔══╝  \n" +
                "            ██████╔╝██║  ██║   ██║   ██║      ╚██████╗╚██████╔╝██║███████║██║██║ ╚████║███████╗\n" +
                "            ╚═════╝ ╚═╝  ╚═╝   ╚═╝   ╚═╝       ╚═════╝ ╚═════╝ ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝╚══════╝\n" +
                "\u001B[0m";
    }


}
