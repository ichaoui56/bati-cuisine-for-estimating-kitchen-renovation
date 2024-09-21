package org.BatiCuisine.repositories.Impl;

import org.BatiCuisine.config.DatabaseConnection;
import org.BatiCuisine.models.entities.Material;
import org.BatiCuisine.repositories.Inter.MaterialRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialRepositoryImpl implements MaterialRepository {
    private final Connection connection;

    public MaterialRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public Material addMaterial(Material material) throws SQLException {
        String query = "INSERT INTO material (nom, taux_tva, type_composant, projet_id, cout_unitaire, quantite, cout_transport, coefficient_qualite) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING *";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, material.getNom());
            pstmt.setDouble(2, material.getTauxTVA());
            pstmt.setString(3, material.getTypeComposant());
            pstmt.setInt(4, material.getProjet().getId());
            pstmt.setDouble(5, material.getCoutUnitaire());
            pstmt.setDouble(6, material.getQuantite());
            pstmt.setDouble(7, material.getCoutTransport());
            pstmt.setDouble(8, material.getCoefficientQualite());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    material.setId(rs.getInt("id"));
                    return material;
                } else {
                    throw new SQLException("Inserting material failed, no data returned.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error while adding material: " + e.getMessage(), e);
        }
    }

    public void updateMaterialTva(int materialId, double tva) throws SQLException {
        String query = "UPDATE material SET taux_tva = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, tva);
            pstmt.setInt(2, materialId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error while updating material TVA: " + e.getMessage(), e);
        }
    }


}
