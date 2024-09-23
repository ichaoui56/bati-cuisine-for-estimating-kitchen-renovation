package org.BatiCuisine.utils;

import org.BatiCuisine.models.entities.Material;
import org.BatiCuisine.models.entities.MainOeuvre;

import java.util.HashMap;

public class DevisCalculation {

    // Calculate total material cost before TVA
    public double calculateTotalMaterialCostBeforeTVA(HashMap<Integer, Material> materials) {
        return materials.values()
                .stream()
                .mapToDouble(material -> (material.getCoutUnitaire() * material.getQuantite()) + material.getCoutTransport())
                .sum();
    }

    // Calculate total material cost with TVA
    public double calculateTotalMaterialCostWithTVA(HashMap<Integer, Material> materials, double tvaRate) {
        double totalBeforeTVA = calculateTotalMaterialCostBeforeTVA(materials);
        return calculateTotalWithTVA(totalBeforeTVA, tvaRate);
    }

    // Calculate total labor cost before TVA
    public double calculateTotalLaborCostBeforeTVA(HashMap<Integer, MainOeuvre> labor) {
        return labor.values()
                .stream()
                .mapToDouble(worker -> worker.getCoutHoraire() * worker.getNombreHeures())
                .sum();
    }

    // Calculate total labor cost with TVA
    public double calculateTotalLaborCostWithTVA(HashMap<Integer, MainOeuvre> labor, double tvaRate) {
        double totalBeforeTVA = calculateTotalLaborCostBeforeTVA(labor);
        return calculateTotalWithTVA(totalBeforeTVA, tvaRate);
    }

    // Calculate total with TVA
    public double calculateTotalWithTVA(double total, double tvaRate) {
        return total * (1 + tvaRate);
    }

    // Calculate total before marge (profit margin)
    public double calculateTotalBeforeMarge(double totalMaterialCostBeforeTVA, double totalLaborCostBeforeTVA) {
        return totalMaterialCostBeforeTVA + totalLaborCostBeforeTVA;
    }

    // Calculate the marge (profit margin)
    public double calculateMarge(double totalBeforeMarge, double margeRate) {
        return totalBeforeMarge * margeRate;
    }

    // Calculate the final total with margin included
    public double calculateFinalTotal(double totalBeforeMarge, double margeRate) {
        double marge = calculateMarge(totalBeforeMarge, margeRate);
        return totalBeforeMarge + marge;
    }
}
