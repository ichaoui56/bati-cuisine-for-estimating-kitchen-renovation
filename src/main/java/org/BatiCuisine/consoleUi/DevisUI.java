package org.BatiCuisine.consoleUi;

import org.BatiCuisine.models.entities.Client;
import org.BatiCuisine.models.entities.MainOeuvre;
import org.BatiCuisine.models.entities.Material;
import org.BatiCuisine.models.entities.Projet;
import org.BatiCuisine.utils.DevisCalculation;

import java.util.HashMap;
import java.util.Scanner;

public class DevisUI {

    private static final String COLOR_96C9F4 = "\u001B[38;5;75m";
    private static final String COLOR_E7F0DC = "\u001B[38;5;146m";
    private static final String RESET_COLOR = "\u001B[0m";

    public void displayDevis(Client client, Projet projet, HashMap<Integer, Material> materials, HashMap<Integer, MainOeuvre> labor, double margeRate, double tvaRate) {
        DevisCalculation calcul = new DevisCalculation();

        double totalMaterialCostBeforeTVA = calcul.calculateTotalMaterialCostBeforeTVA(materials);
        double totalMaterialCostWithTVA = calcul.calculateTotalMaterialCostWithTVA(materials, tvaRate);

        double totalLaborCostBeforeTVA = calcul.calculateTotalLaborCostBeforeTVA(labor);
        double totalLaborCostWithTVA = calcul.calculateTotalLaborCostWithTVA(labor, tvaRate);

        double totalBeforeMarge = calcul.calculateTotalBeforeMarge(totalMaterialCostBeforeTVA, totalLaborCostBeforeTVA);
        double marge = calcul.calculateMarge(totalBeforeMarge, margeRate);
        double finalTotal = calcul.calculateFinalTotal(totalBeforeMarge, margeRate);

        System.out.println(COLOR_96C9F4 + "       ************************************************************************************************");
        System.out.println("       ||                                                                                            ||");
        System.out.println("       ||                                  ***   Résultat du Calcul  ***                             ||");
        System.out.println("       ||                                                                                            ||");
        System.out.println("       ||                                       Nom du projet :" + COLOR_E7F0DC + projet.getNomProjet() + COLOR_96C9F4 + "                                   ||");
        System.out.println("       ||                                       Client        :" + COLOR_E7F0DC + client.getNom() + COLOR_96C9F4 + "                                    ||");
        System.out.println("       ||                                       Adresse       :" + COLOR_E7F0DC + client.getAddress() + COLOR_96C9F4 + "                                    ||");
        System.out.println("       ||                                       Surface       :" + COLOR_E7F0DC + projet.getSurface() + COLOR_96C9F4 + "                                   ||");
        System.out.println("       ||                                                                                            ||");
        System.out.println("       ||********************************************************************************************||");
        System.out.println("       ||                                                                                            ||");
        System.out.println("       ||                                  --- Détail des Coûts ---                                  ||");
        System.out.println("       ||                                                                                            ||");
        System.out.println("       ||                                       1. Matériaux :                                       ||");
        System.out.println("       ||                                                                                            ||");

        displayMaterialDetails(materials);

        System.out.println("       ||                                                                                            ||");
        System.out.println("       ||                      ** Coût total des matériaux avant TVA : " + COLOR_E7F0DC + totalMaterialCostBeforeTVA + " € " + COLOR_96C9F4 + "**                      ||");
        System.out.println("       ||                   ** Coût total des matériaux avec TVA (" + (tvaRate * 100) + "%) : " + COLOR_E7F0DC + totalMaterialCostWithTVA + " €" + COLOR_96C9F4 + " **               ||");
        System.out.println("       ||                                                                                            ||");
        System.out.println("       ||                                       2. Main-d'œuvre :                                    ||");
        System.out.println("       ||                                                                                            ||");

        displayLaborDetails(labor);

        System.out.println("       ||                                                                                            ||");
        System.out.println("       ||                   ** Coût total de la main-d'œuvre avant TVA : " + COLOR_E7F0DC + totalLaborCostBeforeTVA + " € " + COLOR_96C9F4 + "**                    ||");
        System.out.println("       ||                 ** Coût total de la main-d'œuvre avec TVA (" + (tvaRate * 100) + "%) : " + COLOR_E7F0DC + totalLaborCostWithTVA + " € " + COLOR_96C9F4 + "**            ||");
        System.out.println("       ||                                                                                            ||");
        System.out.println("       ||                           3. Coût total avant marge : " + COLOR_E7F0DC + totalBeforeMarge + " € " + COLOR_96C9F4 + "                               ||");
        System.out.println("       ||                                                                                            ||");
        System.out.println("       ||                           4. Marge bénéficiaire (" + (margeRate * 100) + "%) : " + COLOR_E7F0DC + marge + " €   " + COLOR_96C9F4 + "                     ||");
        System.out.println("       ||                                                                                            ||");
        System.out.println("       ||                         ** Coût total final du projet : " + COLOR_E7F0DC + finalTotal + " € **    " + COLOR_96C9F4 + "                     ||");
        System.out.println("       ||                                                                                            ||");
        System.out.println("       ************************************************************************************************" + RESET_COLOR);
        System.out.println("\n");
    }

    private void displayMaterialDetails(HashMap<Integer, Material> materials) {
        materials.values().forEach(material -> {
            System.out.println("       ||     - " + material.getNom() + " : " + COLOR_E7F0DC + ((material.getCoutUnitaire() * material.getQuantite()) + material.getCoutTransport()) + " € (quantité : " + material.getQuantite() + ", coût unitaire : " + material.getCoutUnitaire() + " €/unit, transport : " + material.getCoutTransport() + " €)" + COLOR_96C9F4 + "          ||");
        });
    }

    private void displayLaborDetails(HashMap<Integer, MainOeuvre> labor) {
        labor.values().forEach(worker -> {
            System.out.println("       ||    - " + worker.getNom() + " : " + COLOR_E7F0DC + (worker.getCoutHoraire() * worker.getNombreHeures()) + " € (taux horaire : " + worker.getCoutHoraire() + " €/h, heures travaillées : " + worker.getNombreHeures() + " h)" + COLOR_96C9F4 + "             ||");
        });
    }
}
