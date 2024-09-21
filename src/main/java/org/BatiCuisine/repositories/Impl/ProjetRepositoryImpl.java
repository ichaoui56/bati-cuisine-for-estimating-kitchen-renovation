package org.BatiCuisine.repositories.Impl;

import org.BatiCuisine.config.DatabaseConnection;
import org.BatiCuisine.models.entities.Projet;
import org.BatiCuisine.repositories.Inter.ProjetRepository;

import java.sql.*;

public class ProjetRepositoryImpl implements ProjetRepository {
    private final Connection connection;

    public ProjetRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public Projet addProjet(Projet projet) throws SQLException {
        String query = "INSERT INTO projet (nom_projet, marge_beneficiaire, cout_total, etat, client_id, surface) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, projet.getNomProjet());
            pstmt.setDouble(2, projet.getMargeBeneficiaire());
            pstmt.setDouble(3, projet.getCoutTotal());
            pstmt.setObject(4, projet.getEtat().name(), Types.OTHER);
            pstmt.setInt(5, projet.getClient().getId());
            pstmt.setDouble(6, projet.getSurface());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    projet.setId(id);
                    return projet;
                } else {
                    throw new SQLException("Inserting project failed, no ID returned.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error while adding project: " + e.getMessage(), e);
        }
    }

    public void updateMargeBenef(int projetId, double margeBenef) {
        String sql = "UPDATE projet SET marge_beneficiaire = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, margeBenef);
            stmt.setInt(2, projetId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
