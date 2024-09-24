package org.BatiCuisine.consoleUi;

import org.BatiCuisine.models.entities.*;
import org.BatiCuisine.models.enums.EtatProjet;
import org.BatiCuisine.services.Inter.DevisService;
import org.BatiCuisine.services.Inter.MainOeuvreService;
import org.BatiCuisine.services.Inter.MaterialService;
import org.BatiCuisine.services.Inter.ProjetService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ProjetUI {
    private final Scanner scanner = new Scanner(System.in);
    private ProjetService projetService;
    private final DevisService devisService;
    private final ComposantUI composantUI;
    private final DevisUI devisUI;


    public ProjetUI(ProjetService projetService, ComposantUI composantUI, DevisUI devisUI, DevisService devisService) {
        this.projetService = projetService;
        this.composantUI = composantUI;
        this.devisUI = devisUI;
        this.devisService = devisService;
    }

    public void addProjet(Client client) throws SQLException {
        System.out.println("**===================================|(    \u001B[36m📃   Add Projet   📃\u001B[0m    )|=================================**");
        System.out.println("||                                                                                                     ||");
        System.out.print("||                                       Entrez le nom du projet :");
        String nom = scanner.nextLine().trim();
        System.out.print("||                               Entrez la surface de la cuisine (en m²) : ");
        double surface = Double.parseDouble(scanner.nextLine().trim());
        System.out.println("||                                                                                                     ||");
        System.out.println("**====================================================================================================**");
        System.out.println("\n");

        Projet projet = new Projet(nom, 0.0, 0.0, EtatProjet.EN_COURS, client, surface);
        Projet createdProjet = projetService.ajouterProjet(projet);

        composantUI.addMaterial(createdProjet, client, this);
    }

    public void finalizeProjet(Client client, Projet projet, HashMap<Integer, Material> material,  HashMap<Integer, MainOeuvre> mainOeuvre) throws SQLException {
        System.out.println("**===========================|(       \u001B[36m🔚   Calcul du coût total   🔚\u001B[0m       )|===========================**");
        System.out.println("||                                                                                                     ||");
        System.out.print("||                            Voulez-vous ajouter la TVA? (oui/non) : ");
        String addTvaResponse = scanner.nextLine().trim().toLowerCase();

        double tva = 0.0;
        if (addTvaResponse.equals("oui")) {
            System.out.println("||                                                                                                     ||");
            System.out.print("||                   Entrez le pourcentage de TVA (ex. 20 pour 20%) : ");
            tva = Double.parseDouble(scanner.nextLine().trim()) / 100.0;
        }

        System.out.println("||                                                                                                     ||");
        System.out.print("||                      Voulez-vous ajouter la marge bénéficiaire? (oui/non) : ");
        String addMargeResponse = scanner.nextLine().trim().toLowerCase();

        double margeBenef = 0.0;
        if (addMargeResponse.equals("oui")) {
            System.out.println("||                                                                                                     ||");
            System.out.print("||                Entrez le pourcentage de marge bénéficiaire (ex. 15 pour 15%) : ");
            margeBenef = Double.parseDouble(scanner.nextLine().trim()) / 100.0;
        }
        System.out.println("||                                                                                                     ||");
        System.out.println("**====================================================================================================**");

        composantUI.updateTva(tva);

        System.out.println("\n");
        System.out.println("\n");
        System.out.println("||                                    ⌛ Calcul du coût en cours... ⏳                                  ||");

        devisUI.displayDevis(client, projet, material, mainOeuvre, margeBenef, tva);

    }

    public void displayAllProjects(){
        System.out.println("\n\n");
        System.out.println("**===============================|(    \u001B[36m📂   Liste des Projets   📂\u001B[0m    )|===============================**");
        System.out.println("\n");

        Optional<Map<Integer, Projet>> optionalProjects = projetService.fetchAllProjects();

        if (optionalProjects.isPresent() && !optionalProjects.get().isEmpty()) {
            for (Map.Entry<Integer, Projet> entry : optionalProjects.get().entrySet()) {
                Projet projet = entry.getValue();

                System.out.println("        **=======================|(    \u001B[36m📂   Projet number " + projet.getId() + "   📂\u001B[0m    )|=========================**");
                System.out.println("        ||                                                                                       ||");

                System.out.println("          Nom: " + projet.getNomProjet()
                        + " | Marge: " + String.format("%.2f", projet.getMargeBeneficiaire())
                        + " | Coût Total: " + String.format("%.2f", projet.getCoutTotal())
                        + " | Surface: " + String.format("%.2f", projet.getSurface())
                        + " | État: " + projet.getEtat());

                System.out.println("               Nom: " + projet.getClient().getNom()
                        + " | Address: " + projet.getClient().getAddress()
                        + " | Phone Number: " + projet.getClient().getPhoneNumber()
                        + " | Type: " + projet.getClient().isEstProfessionnal());

                System.out.println("        ||                                                                                       ||");
                System.out.println("        **=======================================================================================**");
                System.out.println("\n");
            }
        } else {
            System.out.println("Aucun projet trouvé.");
        }
        System.out.println("\n");
        System.out.println("**====================================================================================================**");
        System.out.println("\n\n");
    }

    public void displayProjets(PrincipalUI principalUI) throws SQLException {
        int choix = 0;
        do {
            System.out.println("\n");
            System.out.println("**=================================|(    \u001B[32m📃   Menu projet   📃\u001B[0m    )|=================================**");
            System.out.println("||                                                                                                    ||");
            System.out.println("||                                    1. Chercher un projet existant                                  ||");
            System.out.println("||                                    2. Quitter                                                      ||");
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
                    searchProjet();
                    break;
                case 2:
                    principalUI.displayMenu();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 3);
    }

    public void searchProjet() throws SQLException {

        boolean continueSearching;
        do {
            continueSearching = false;
            System.out.print("                                  Entrez le nom du projet à chercher : ");
            String name = scanner.nextLine().trim();

            Map<Integer, Projet> projets = projetService.searchProjetByName(name);

            if (projets.isEmpty()) {
                System.out.println("                                Aucun projet trouvé avec le nom : " + name);
            } else if (projets.size() > 1) {
                displayProjetSuggestions(projets);
                selectProjet(projets);
            } else {
                Projet projet = projets.values().iterator().next();
                displayProjetDetails(projet);

                System.out.print("                            Souhaitez-vous continuer avec ce projet ? (oui/non) : ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("oui")) {
                    displayProjectCostEstimate(projet);
                } else {
                    continueSearching = true;
                }
            }
        } while (continueSearching);
    }

    private void selectProjet(Map<Integer, Projet> projets) throws SQLException {
        System.out.print("                  Veuillez entrer l'ID du projet pour continuer : ");
        String input = scanner.nextLine().trim();
        System.out.println("\n");
        try {
            int projetId = Integer.parseInt(input);
            if (projets.containsKey(projetId)) {
                Projet selectedProjet = projets.get(projetId);
                displayProjectCostEstimate(selectedProjet);
            } else {
                System.out.println("                                ID de projet invalide. Veuillez réessayer.");
            }
        } catch (NumberFormatException e) {
            System.out.println("                                        Veuillez entrer un ID valide.");
        }
    }

    private void displayProjetDetails(Projet projet) {
        System.out.println("\n");
        System.out.println("        **=========================|(\u001B[32m📃   Résultats de la recherche   📃\u001B[0m)|=======================**");
        System.out.println("        ||                                                                                       ||");
        System.out.println("               ID : " + projet.getId()
                + " | Nom : " + projet.getNomProjet()
                + " | Etat : " + projet.getEtat()
                + " | Marge Beneficiaire : " + projet.getMargeBeneficiaire()
                + "\n                                   Surface : " + projet.getSurface()
                + " | Cout Total : " + projet.getCoutTotal());

        System.out.println("        ||                                                                                       ||");
        System.out.println("        **=======================================================================================**");
    }

    private void displayProjetSuggestions(Map<Integer, Projet> projets) {
        System.out.println("\n");
        System.out.println("        **=============================|(\u001B[32m📃   Projets trouvés   📃\u001B[0m)|===========================**");
        System.out.println("        ||                                                                                       ||");

        projets.forEach((id, projet) -> {
            System.out.println("               ID : " + projet.getId()
                    + " | Nom : " + projet.getNomProjet()
                    + " | Etat : " + projet.getEtat()
                    + " | Marge Beneficiaire : " + projet.getMargeBeneficiaire()
                    + "\n                                   Surface : " + projet.getSurface()
                    + " | Cout Total : " + projet.getCoutTotal());
        });
        System.out.println("        ||                                                                                       ||");
        System.out.println("        **=======================================================================================**");
        System.out.println("\n");
    }

    private void displayProjectCostEstimate(Projet projet) throws SQLException {
        System.out.println("\n        **===================================|(\u001B[32m📄   Cout Total   📄\u001B[0m)|===============================**");
        System.out.println("        ||                                                                                       ||");

        Devis devis = devisService.getDevisByProjetId(projet.getId());

        if (devis != null) {
            double totalCost = devis.getMontantEstime();

            System.out.println("                            Coût Total total pour le projet '" + projet.getNomProjet() + "' : "
                    + String.format("%.2f", totalCost) + " MAD");
        } else {
            System.out.println("               Aucun devis trouvé pour le projet '" + projet.getNomProjet() + "'.");
        }

        System.out.println("        ||                                                                                       ||");
        System.out.println("        **=======================================================================================**");
    }


}
