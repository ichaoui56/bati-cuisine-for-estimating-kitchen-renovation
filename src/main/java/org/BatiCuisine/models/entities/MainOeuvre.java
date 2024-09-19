package org.BatiCuisine.models.entities;

public class MainOeuvre extends Composant {

    private double coutHoraire;
    private int nombreHeures;

    public MainOeuvre() {}

    public MainOeuvre(String nom, double tauxTVA, String typeComposant, Projet projet, double coutHoraire, int nombreHeures) {
        super(nom, tauxTVA, typeComposant, projet);
        this.coutHoraire = coutHoraire;
        this.nombreHeures = nombreHeures;
    }

    public double getCoutHoraire() { return coutHoraire; }

    public void setCoutHoraire(double coutHoraire) { this.coutHoraire = coutHoraire; }

    public int getNombreHeures() { return nombreHeures; }

    public void setNombreHeures(int nombreHeures) { this.nombreHeures = nombreHeures; }

    @Override
    public String toString() {
        return "MainOeuvre{" +
                "coutHoraire=" + coutHoraire +
                ", nombreHeures=" + nombreHeures +
                "} " + super.toString();
    }
}
