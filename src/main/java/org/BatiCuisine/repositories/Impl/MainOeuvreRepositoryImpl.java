package org.BatiCuisine.repositories.Impl;

import org.BatiCuisine.config.DatabaseConnection;
import org.BatiCuisine.models.entities.MainOeuvre;
import org.BatiCuisine.repositories.Inter.MainOeuvreRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainOeuvreRepositoryImpl implements MainOeuvreRepository {

    private final Connection connection;

    public MainOeuvreRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public MainOeuvre addLabor(MainOeuvre mainOeuvre) throws SQLException {
        String query = "INSERT INTO mainoeuvre (nom, taux_tva, type_composant, projet_id, taux_horaire, heures_travail, productivite_ouvrier) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING *";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, mainOeuvre.getNom());
            pstmt.setDouble(2, mainOeuvre.getTauxTVA());
            pstmt.setString(3, mainOeuvre.getTypeComposant());
            pstmt.setInt(4, mainOeuvre.getProjet().getId());
            pstmt.setDouble(5, mainOeuvre.getCoutHoraire());
            pstmt.setDouble(6, mainOeuvre.getNombreHeures());
            pstmt.setDouble(7, mainOeuvre.getProductiviteOuvrier());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    mainOeuvre.setId(rs.getInt(1));
                    return mainOeuvre;
                } else {
                    throw new SQLException("Inserting material failed, no data returned.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error while adding material: " + e.getMessage(), e);
        }
    }

    public void updateMainOeuvreTva(int mainOeuvreId, double tva) throws SQLException {
        String query = "UPDATE mainoeuvre SET taux_tva = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, tva);
            pstmt.setInt(2, mainOeuvreId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error while updating main oeuvre TVA: " + e.getMessage(), e);
        }
    }

}
