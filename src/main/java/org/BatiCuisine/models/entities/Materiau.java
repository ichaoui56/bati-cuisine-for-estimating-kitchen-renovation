package org.BatiCuisine.models.entities;

public class Materiau extends Composant{


    private double coutUnitaire;
    private double quantite;
    private double coutTransport;
    private double coefficientQualite;

    private Composant composant;

    public Materiau() {}

    public Materiau(double coutUnitaire, double quantite, double coutTransport, double coefficientQualite, String nom, double tauxTVA, String typeComposant, Projet projet) {
        super(nom, tauxTVA, typeComposant, projet);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public double getCoutUnitaire() { return coutUnitaire; }

    public void setCoutUnitaire(double coutUnitaire) { this.coutUnitaire = coutUnitaire; }

    public double getQuantite() { return quantite; }

    public void setQuantite(double quantite) { this.quantite = quantite; }

    public double getCoutTransport() { return coutTransport; }

    public void setCoutTransport(double coutTransport) { this.coutTransport = coutTransport; }

    public double getCoefficientQualite() { return coefficientQualite; }

    public void setCoefficientQualite(double coefficientQualite) { this.coefficientQualite = coefficientQualite; }

    public Composant getComposant() { return composant; }

    public void setComposant(Composant composant) { this.composant = composant; }

    @Override
    public String toString() {
        return "Materiau{" +
                ", coutUnitaire=" + coutUnitaire +
                ", quantite=" + quantite +
                ", coutTransport=" + coutTransport +
                ", coefficientQualite=" + coefficientQualite +
                ", composant=" + composant +
                '}' + super.toString();
    }
}

