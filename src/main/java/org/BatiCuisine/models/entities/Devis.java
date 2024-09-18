package org.BatiCuisine.models.entities;

import java.util.List;

public class Devis {

    private int id;
    private double montantHT;
    private double montantTTC;

    private Projet projet;
    private List<Materiau> materiaux;
    private List<MainOeuvre> mainDoeuvres;

    public Devis() {}

    public Devis(double montantHT, double montantTTC, Projet projet) {
        this.montantHT = montantHT;
        this.montantTTC = montantTTC;
        this.projet = projet;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public double getMontantHT() { return montantHT; }

    public void setMontantHT(double montantHT) { this.montantHT = montantHT; }

    public double getMontantTTC() { return montantTTC; }

    public void setMontantTTC(double montantTTC) { this.montantTTC = montantTTC; }

    public Projet getProjet() { return projet; }

    public void setProjet(Projet projet) { this.projet = projet; }

    public List<Materiau> getMateriaux() { return materiaux; }

    public void setMateriaux(List<Materiau> materiaux) { this.materiaux = materiaux; }

    public List<MainOeuvre> getMainDoeuvres() { return mainDoeuvres; }

    public void setMainDoeuvres(List<MainOeuvre> mainDoeuvres) { this.mainDoeuvres = mainDoeuvres; }

    @Override
    public String toString() {
        return "Devis{" +
                "id=" + id +
                ", montantHT=" + montantHT +
                ", montantTTC=" + montantTTC +
                ", projet=" + projet +
                '}';
    }
}

