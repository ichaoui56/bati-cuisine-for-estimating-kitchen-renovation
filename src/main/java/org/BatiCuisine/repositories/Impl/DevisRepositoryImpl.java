package org.BatiCuisine.repositories.Impl;

import org.BatiCuisine.config.DatabaseConnection;
import org.BatiCuisine.models.entities.Devis;
import org.BatiCuisine.repositories.Inter.DevisRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
