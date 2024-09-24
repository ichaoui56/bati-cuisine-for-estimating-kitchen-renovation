package org.BatiCuisine.repositories.Impl;

import org.BatiCuisine.config.DatabaseConnection;
import org.BatiCuisine.models.entities.*;
import org.BatiCuisine.models.enums.EtatProjet;
import org.BatiCuisine.repositories.Inter.DevisRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DevisRepositoryImpl implements DevisRepository {

    private final Connection connection;

    public DevisRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void createDevis(Devis devis) {
        String query = "INSERT INTO devis (montant_estime, date_emission, date_validite, accepte, projet_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, devis.getMontantEstime());
            pstmt.setDate(2, Date.valueOf(devis.getDateEmission()));
            pstmt.setDate(3, Date.valueOf(devis.getDateValidite()));
            pstmt.setBoolean(4, devis.isAccepte());
            pstmt.setInt(5, devis.getProjet().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL Error while creating Devis: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Devis getDevisByProjetId(int projetId) {
        String query = "SELECT * FROM devis WHERE projet_id = ?";
        Devis devis = null;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, projetId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int devisId = rs.getInt("id");
                double montantEstime = rs.getDouble("montant_estime");
                LocalDate dateEmission = rs.getDate("date_emission").toLocalDate();
                LocalDate dateValidite = rs.getDate("date_validite").toLocalDate();
                boolean accepte = rs.getBoolean("accepte");

                Projet projet = new Projet();
                projet.setId(projetId);

                devis = new Devis(montantEstime, dateEmission, dateValidite, accepte, projet);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while fetching Devis by project ID: " + e.getMessage());
            e.printStackTrace();
        }

        return devis;
    }

}
