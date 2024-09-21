package org.BatiCuisine.consoleUi;

import org.BatiCuisine.models.entities.Client;
import org.BatiCuisine.models.entities.MainOeuvre;
import org.BatiCuisine.models.entities.Material;
import org.BatiCuisine.models.entities.Projet;
import org.BatiCuisine.models.enums.EtatProjet;
import org.BatiCuisine.services.Inter.MainOeuvreService;
import org.BatiCuisine.services.Inter.MaterialService;
import org.BatiCuisine.services.Inter.ProjetService;

import java.sql.SQLException;
import java.util.Scanner;

public class ProjetUI {
    private final Scanner scanner = new Scanner(System.in);
    private ProjetService projetService;
    private final ComposantUI composantUI;
    private final DevisUI devisUI;


    public ProjetUI(ProjetService projetService, ComposantUI composantUI, DevisUI devisUI) {
        this.projetService = projetService;
        this.composantUI = composantUI;
        this.devisUI = devisUI;
    }

    public void addProjet(Client client) throws SQLException {
        System.out.println("**=================================|(    📃   Add Project   📃    )|=================================**");
        System.out.println("||                                                                                                   ||");
        System.out.print("||                                       Entrez le nom du projet :");
        String nom = scanner.nextLine().trim();
        System.out.print("||                               Entrez la surface de la cuisine (en m²) : ");
        double surface = Double.parseDouble(scanner.nextLine().trim());
        System.out.println("||                                                                                                   ||");
        System.out.println("**====================================================================================================**");
        System.out.println("\n");

        Projet projet = new Projet(nom, 0.0, 0.0, EtatProjet.EN_COURS, client, surface);
        Projet createdProjet = projetService.ajouterProjet(projet);

        composantUI.addMaterial(createdProjet);
        composantUI.addLabor(createdProjet);

        finalizeProjet(client, projet);
    }

    public void finalizeProjet(Client client, Projet projet) throws SQLException {
        System.out.println("**===========================|(    🔚   Calcul du coût total   🔚    )|===========================**");
        System.out.println("\n");
        System.out.print("||                            Voulez-vous ajouter la TVA? (oui/non) : ");
        String addTvaResponse = scanner.nextLine().trim().toLowerCase();

        double tva = 0.0;
        if (addTvaResponse.equals("oui")) {
            System.out.println("\n");
            System.out.print("||                   Entrez le pourcentage de TVA (ex. 20 pour 20%) : ");
            tva = Double.parseDouble(scanner.nextLine().trim()) / 100.0;
        }

        System.out.println("\n");
        System.out.print("||                      Voulez-vous ajouter la marge bénéficiaire? (oui/non) : ");
        String addMargeResponse = scanner.nextLine().trim().toLowerCase();

        double margeBenef = 0.0;
        if (addMargeResponse.equals("oui")) {
            System.out.println("\n");
            System.out.print("||                Entrez le pourcentage de marge bénéficiaire (ex. 15 pour 15%) : ");
            margeBenef = Double.parseDouble(scanner.nextLine().trim()) / 100.0;
        }

        composantUI.updateTva(tva);

        projetService.modifierMargeBenef(projet.getId(), margeBenef);

        System.out.println("\n");
        System.out.println("\n");
        System.out.println("||                                  Calcul du coût en cours...                                  ||");

    }


}
