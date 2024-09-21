package org.BatiCuisine.models.entities;

public class MainOeuvre extends Composant {

    private double coutHoraire;
    private double nombreHeures;
    private double productiviteOuvrier;
    public MainOeuvre() {}

    public MainOeuvre(String nom, double tauxTVA, String typeComposant, Projet projet, double coutHoraire, double nombreHeures, double productiviteOuvrier) {
        super(nom, tauxTVA, typeComposant, projet);
        this.coutHoraire = coutHoraire;
        this.nombreHeures = nombreHeures;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public double getCoutHoraire() { return coutHoraire; }

    public void setCoutHoraire(double coutHoraire) { this.coutHoraire = coutHoraire; }

    public double getNombreHeures() { return nombreHeures; }

    public void setNombreHeures(double nombreHeures) { this.nombreHeures = nombreHeures; }

    public double getProductiviteOuvrier() { return productiviteOuvrier; }
    
    public void setProductiviteOuvrier(double productiviteOuvrier) {this.productiviteOuvrier = productiviteOuvrier; }
    @Override
    public String toString() {
        return "MainOeuvre{" +
                "coutHoraire=" + coutHoraire +
                ", nombreHeures=" + nombreHeures +
                "} " + super.toString();
    }
}
