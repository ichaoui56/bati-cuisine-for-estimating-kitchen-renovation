package org.BatiCuisine.repositories.Impl;

import org.BatiCuisine.config.DatabaseConnection;
import org.BatiCuisine.models.entities.MainOeuvre;
import org.BatiCuisine.repositories.Inter.MainOeuvreRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainOeuvreRepositoryImpl implements MainOeuvreRepository {

    private final Connection connection;

    public MainOeuvreRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void addLabor(MainOeuvre mainOeuvre) throws SQLException {
        String query = "INSERT INTO mainoeuvre (nom, taux_tva, type_composant, projet_id, taux_horaire, heures_travail, productivite_ouvrier) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, mainOeuvre.getNom());
            pstmt.setDouble(2, mainOeuvre.getTauxTVA());
            pstmt.setString(3, mainOeuvre.getTypeComposant());
            pstmt.setInt(4, mainOeuvre.getProjet().getId());
            pstmt.setDouble(5, mainOeuvre.getCoutHoraire());
            pstmt.setDouble(6, mainOeuvre.getNombreHeures());
            pstmt.setDouble(7, mainOeuvre.getProductiviteOuvrier());

            pstmt.executeUpdate();
        }
    }



}
