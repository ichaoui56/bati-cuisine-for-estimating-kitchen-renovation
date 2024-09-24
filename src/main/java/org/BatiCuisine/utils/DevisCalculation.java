package org.BatiCuisine.utils;

import org.BatiCuisine.models.entities.Material;
import org.BatiCuisine.models.entities.MainOeuvre;

import java.util.HashMap;

public class DevisCalculation {

    public double calculateTotalMaterialCostBeforeTVA(HashMap<Integer, Material> materials) {
        return materials.values()
                .stream()
                .mapToDouble(material -> (material.getCoutUnitaire() * material.getQuantite()) + material.getCoutTransport())
                .sum();
    }

    public double calculateTotalMaterialCostWithTVA(HashMap<Integer, Material> materials, double tvaRate) {
        double totalBeforeTVA = calculateTotalMaterialCostBeforeTVA(materials);
        return calculateTotalWithTVA(totalBeforeTVA, tvaRate);
    }

    public double calculateTotalLaborCostBeforeTVA(HashMap<Integer, MainOeuvre> labor) {
        return labor.values()
                .stream()
                .mapToDouble(worker -> worker.getCoutHoraire() * worker.getNombreHeures())
                .sum();
    }

    public double calculateTotalLaborCostWithTVA(HashMap<Integer, MainOeuvre> labor, double tvaRate) {
        double totalBeforeTVA = calculateTotalLaborCostBeforeTVA(labor);
        return calculateTotalWithTVA(totalBeforeTVA, tvaRate);
    }

    public double calculateTotalWithTVA(double total, double tvaRate) {
        return total * (1 + tvaRate);
    }

    public double calculateTotalBeforeMarge(double totalMaterialCostBeforeTVA, double totalLaborCostBeforeTVA) {
        return totalMaterialCostBeforeTVA + totalLaborCostBeforeTVA;
    }

    public double calculateMarge(double totalBeforeMarge, double margeRate) {
        return totalBeforeMarge * margeRate;
    }

    public double calculateFinalTotal(double totalBeforeMarge, double margeRate) {
        double marge = calculateMarge(totalBeforeMarge, margeRate);
        return totalBeforeMarge + marge;
    }
}
