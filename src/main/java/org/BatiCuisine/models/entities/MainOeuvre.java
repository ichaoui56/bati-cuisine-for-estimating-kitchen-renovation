package org.BatiCuisine.models.entities;

public class MainOeuvre {

    private int id;
    private double coutHoraire;
    private int nombreHeures;

    private Composant composant;

    public MainOeuvre() {}

    public MainOeuvre(double coutHoraire, int nombreHeures, Composant composant) {
        this.coutHoraire = coutHoraire;
        this.nombreHeures = nombreHeures;
        this.composant = composant;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public double getCoutHoraire() { return coutHoraire; }

    public void setCoutHoraire(double coutHoraire) { this.coutHoraire = coutHoraire; }

    public int getNombreHeures() { return nombreHeures; }

    public void setNombreHeures(int nombreHeures) { this.nombreHeures = nombreHeures; }

    public Composant getComposant() { return composant; }

    public void setComposant(Composant composant) { this.composant = composant; }

    @Override
    public String toString() {
        return "MainOeuvre{" +
                "id=" + id +
                ", coutHoraire=" + coutHoraire +
                ", nombreHeures=" + nombreHeures +
                ", composant=" + composant +
                '}';
    }
}

