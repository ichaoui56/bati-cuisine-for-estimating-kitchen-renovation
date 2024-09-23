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
import java.util.HashMap;
import java.util.Scanner;

public class ComposantUI {
    private final Scanner scanner = new Scanner(System.in);
    private final MaterialService materialService;
    private final MainOeuvreService mainOeuvreService;
    private final HashMap<Integer, Material> addedMaterials = new HashMap<>();
    private final HashMap<Integer, MainOeuvre> addedLabors = new HashMap<>();

    public ComposantUI(MaterialService materialService, MainOeuvreService mainOeuvreService) {
        this.materialService = materialService;
        this.mainOeuvreService = mainOeuvreService;
    }

    public void addMaterial(Projet projet, Client client, ProjetUI projetUI) throws SQLException {
        boolean addMoreMaterials = true;

        while (addMoreMaterials) {
            System.out.println("\n");
            System.out.println("**===============================|(   \u001B[36m🧱   Ajouter Material   🧱\u001B[0m   )|=================================**");
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
            System.out.println("**======================================================================================================**");
            System.out.println("\n");

            Material material = new Material(coutUnitaire, quantite, coutTransport, coeffQualite, nomMateriel, 0.0, "Matériel", projet);
            Material materialObj = materialService.ajouterMaterial(material);
            addedMaterials.put(materialObj.getId(), materialObj);

            if (materialObj.getId() > 0) {
                System.out.println("                                     \u001B[32m ✅Matériau ajouté avec succès✅ \u001B[0m ");
                System.out.println("\n");
            } else {
                System.out.println("                                      ❌Matériau n'a pas été ajouter❌");
                System.out.println("\n");
            }

            System.out.print("                              Voulez-vous ajouter un autre matériau? (oui/non) : ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (!response.equals("oui")) {
                addMoreMaterials = false;
                addLabor(client, projet, addedMaterials, projetUI);
            }
        }
    }

    public void addLabor(Client client, Projet projet, HashMap<Integer, Material> material, ProjetUI projetUI) throws SQLException {
        boolean addMoreLabors = true;

        while (addMoreLabors) {
            System.out.println("\n");
            System.out.println("**================================|(    \u001B[36m👷   Ajouter MainOeuvre   👷\u001B[0m    )|===========================**");
            System.out.println("||                                                                                                   ||");
            System.out.print("||               Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
            String typeMainDoeuvre = scanner.nextLine().trim();
            System.out.print("||                        Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            double tauxHoraire = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("||                            Entrez le nombre d'heures travaillées : ");
            double heuresTravaillees = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("||           Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            double facteurProductivite = Double.parseDouble(scanner.nextLine().trim());
            System.out.println("||                                                                                                   ||");
            System.out.println("**===================================================================================================**");
            System.out.println("\n");

            MainOeuvre mainOeuvre = new MainOeuvre(typeMainDoeuvre, 0, "Main-d'œuvre", projet, tauxHoraire, heuresTravaillees, facteurProductivite);
            MainOeuvre mainOeuvreObj = mainOeuvreService.ajouterMainOeuvre(mainOeuvre);
            addedLabors.put(mainOeuvre.getId(), mainOeuvre);

            if (mainOeuvreObj.getId() > 0) {
                System.out.println("                                   ✅\u001B[32m Main-d'œuvre ajoutée avec succès✅ \u001B[0m ");
                System.out.println("\n");
            } else {
                System.out.println("                                   ❌Main-d'œuvre n'a pas été ajouter❌");
                System.out.println("\n");
            }

            System.out.print("                      Voulez-vous ajouter un autre type de main-d'œuvre ? (oui/non) : ");
            String response = scanner.nextLine().trim().toLowerCase();
            System.out.println("\n");


            if (!response.equals("oui")) {
                addMoreLabors = false;
                projetUI.finalizeProjet(client,projet,material,addedLabors);
            }
        }
    }

    public void updateTva(double tva) throws SQLException {
        for (Material material : addedMaterials.values()) {
            material.setTauxTVA(tva);
            materialService.modifierMaterialTva(material.getId(), tva);
        }

        for (MainOeuvre mainOeuvre : addedLabors.values()) {
            mainOeuvre.setTauxTVA(tva);
            mainOeuvreService.modifierMainOeuvreTva(mainOeuvre.getId(), tva);
        }
    }


}
