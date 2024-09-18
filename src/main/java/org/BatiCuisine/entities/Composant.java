package org.BatiCuisine.entities;

import java.util.List;

public class Composant {

    private int id;
    private String nom;
    private double tauxTVA;
    private String typeComposant;

    private Projet projet;
    private List<Materiau> materiaux;
    private List<MainOeuvre> mainDoeuvres;

    public Composant() {}

    public Composant(String nom, double tauxTVA, String typeComposant, Projet projet) {
        this.nom = nom;
        this.tauxTVA = tauxTVA;
        this.typeComposant = typeComposant;
        this.projet = projet;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public double getTauxTVA() { return tauxTVA; }

    public void setTauxTVA(double tauxTVA) { this.tauxTVA = tauxTVA; }

    public String getTypeComposant() { return typeComposant; }

    public void setTypeComposant(String typeComposant) { this.typeComposant = typeComposant; }

    public Projet getProjet() { return projet; }

    public void setProjet(Projet projet) { this.projet = projet; }

    public List<Materiau> getMateriaux() { return materiaux; }

    public void setMateriaux(List<Materiau> materiaux) { this.materiaux = materiaux; }

    public List<MainOeuvre> getMainDoeuvres() { return mainDoeuvres; }

    public void setMainDoeuvres(List<MainOeuvre> mainDoeuvres) { this.mainDoeuvres = mainDoeuvres; }

    @Override
    public String toString() {
        return "Composant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", tauxTVA=" + tauxTVA +
                ", typeComposant='" + typeComposant + '\'' +
                ", projet=" + projet +
                '}';
    }
}

