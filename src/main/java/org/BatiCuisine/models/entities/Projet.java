package org.BatiCuisine.models.entities;

import org.BatiCuisine.models.enums.EtatProjet;

public class Projet {

    private int id;
    private String nomProjet;
    private double margeBeneficiaire;
    private double coutTotal;
    private double surface;
    private EtatProjet etat;

    private Client client;

    public Projet() {}

    public Projet(String nomProjet, double margeBeneficiaire, double coutTotal, EtatProjet etat, Client client, double surface) {
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.surface = surface;
        this.etat = etat;
        this.client = client;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNomProjet() { return nomProjet; }

    public void setNomProjet(String nomProjet) { this.nomProjet = nomProjet; }

    public double getMargeBeneficiaire() { return margeBeneficiaire; }

    public double getSurface() { return surface; }

    public void setSurface(double surface) { this.surface = surface; }

    public void setMargeBeneficiaire(double margeBeneficiaire) { this.margeBeneficiaire = margeBeneficiaire; }

    public double getCoutTotal() { return coutTotal; }

    public void setCoutTotal(double coutTotal) { this.coutTotal = coutTotal; }

    public EtatProjet getEtat() { return etat; }

    public void setEtat(EtatProjet etat) { this.etat = etat; }

    public Client getClient() { return client; }

    public void setClient(Client client) { this.client = client; }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", nomProjet='" + nomProjet + '\'' +
                ", margeBeneficiaire=" + margeBeneficiaire +
                ", coutTotal=" + coutTotal +
                ", etat=" + etat +
                ", client=" + client +
                ", surface=" + surface +
                '}';
    }
}

