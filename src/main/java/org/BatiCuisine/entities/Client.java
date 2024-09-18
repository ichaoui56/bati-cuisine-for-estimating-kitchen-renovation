package org.BatiCuisine.entities;

import java.util.List;

public class Client {

    private int id;
    private String nom;
    private String address;
    private String phoneNumber;
    private boolean estProfessionnel;

    private List<Projet> projets;

    public Client() {}

    public Client(String nom, String address, String phoneNumber, boolean estProfessionnel) {
        this.nom = nom;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.estProfessionnel = estProfessionnel;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public boolean isEstProfessionnel() { return estProfessionnel; }

    public void setEstProfessionnel(boolean estProfessionnel) { this.estProfessionnel = estProfessionnel; }

    public List<Projet> getProjets() { return projets; }

    public void setProjets(List<Projet> projets) { this.projets = projets; }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", estProfessionnel=" + estProfessionnel +
                '}';
    }
}

