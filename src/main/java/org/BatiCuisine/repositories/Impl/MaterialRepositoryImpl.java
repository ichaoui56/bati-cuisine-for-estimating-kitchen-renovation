package org.BatiCuisine.repositories.Impl;

import org.BatiCuisine.config.DatabaseConnection;
import org.BatiCuisine.models.entities.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaterialRepositoryImpl {
    private final Connection connection;

    public MaterialRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void addMaterial(Material material) throws SQLException {
        String query = "INSERT INTO material (nom, taux_tva, type_composant, projet_id, cout_unitaire, quantite, cout_transport, coefficient_qualite) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, material.getNom());
            pstmt.setDouble(2, material.getTauxTVA());
            pstmt.setString(3, material.getTypeComposant());
            pstmt.setInt(4, material.getProjet().getId());
            pstmt.setDouble(5, material.getCoutUnitaire());
            pstmt.setDouble(6, material.getQuantite());
            pstmt.setDouble(7, material.getCoutTransport());
            pstmt.setDouble(8, material.getCoefficientQualite());

            pstmt.executeUpdate();
        }
    }

}
