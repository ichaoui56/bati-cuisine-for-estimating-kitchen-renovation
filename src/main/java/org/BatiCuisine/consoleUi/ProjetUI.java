package org.BatiCuisine.consoleUi;

import java.util.Scanner;

public class ProjetUI {
    private final Scanner scanner = new Scanner(System.in);


    public void addProjet(int id){
        System.out.println("**=================================|(    📃   Add Project   📃    )|=================================**");
        System.out.println("||                                                                                                   ||");
        System.out.print("||                                       Entrez le nom du projet :");
        String nom = scanner.nextLine().trim();
        System.out.print("||                               Entrez la surface de la cuisine (en m²) : ");
        String surface = scanner.nextLine().trim();
        System.out.println("||                                                                                                   ||");
        System.out.println("**====================================================================================================**");
        System.out.println("\n");
        addMaterial();
    }

    public void addMaterial() {
        boolean addMoreMaterials = true;

        while (addMoreMaterials) {
            System.out.println("\n");
            System.out.println("**=================================|(    🧱   Add Material   🧱    )|=================================**");
            System.out.println("||                                                                                                     ||");
            System.out.print("||                                       Entrez le nom du matériau : ");
            String nomMateriel = scanner.nextLine().trim();
            System.out.print("||                                Entrez la quantité de ce matériau (en m²) : ");
            double quantite = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("||                               Entrez le coût unitaire de ce matériau (€/m²) : ");
            double coutUnitaire = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("||                               Entrez le coût de transport de ce matériau (€) : ");
            double coutTransport = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("||         Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            double coeffQualite = Double.parseDouble(scanner.nextLine().trim());
            System.out.println("||                                                                                                     ||");
            System.out.println("**====================================================================================================**");
            System.out.println("\n");
            System.out.println("                                      Matériau ajouté avec succès !");
            System.out.println("\n");

            System.out.print("                              Voulez-vous ajouter un autre matériau? (oui/non) : ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (!response.equals("oui")) {
                addMoreMaterials = false;
                addLabor();
            }
        }
    }

    public void addLabor() {
        boolean addMoreLabors = true;

        while (addMoreLabors) {
            System.out.println("\n");
            System.out.println("**================================|(    👷   Add Labor   👷    )|================================**");
            System.out.println("||                                                                                                 ||");
            System.out.print("||               Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
            String typeMainDoeuvre = scanner.nextLine().trim();
            System.out.print("||                        Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            double tauxHoraire = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("||                            Entrez le nombre d'heures travaillées : ");
            double heuresTravaillees = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("||           Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            double facteurProductivite = Double.parseDouble(scanner.nextLine().trim());
            System.out.println("||                                                                                                 ||");
            System.out.println("**================================================================================================**");
            System.out.println("\n");
            System.out.println("                                   Main-d'œuvre ajoutée avec succès !");
            System.out.println("\n");

            System.out.print("                      Voulez-vous ajouter un autre type de main-d'œuvre ? (oui/non) : ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (!response.equals("oui")) {
                addMoreLabors = false;
            }
        }
    }


}
