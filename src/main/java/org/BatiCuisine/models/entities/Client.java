package org.BatiCuisine.models.entities;

import java.util.List;

public class Client {

    private int id;
    private String nom;
    private String address;
    private String phoneNumber;
    private boolean estProfessionnal;

    private List<Projet> projets;

    public Client() {}

    public Client(String nom, String address, String phoneNumber, boolean estProfessionnal) {
        this.nom = nom;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.estProfessionnal = estProfessionnal;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public boolean isEstProfessionnal() { return estProfessionnal; }

    public void setEstProfessionnal(boolean estProfessionnel) { this.estProfessionnal = estProfessionnel; }

    public List<Projet> getProjets() { return projets; }

    public void setProjets(List<Projet> projets) { this.projets = projets; }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", estProfessionnel=" + estProfessionnal +
                '}';
    }
}

