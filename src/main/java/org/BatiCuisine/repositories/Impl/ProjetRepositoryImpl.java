package org.BatiCuisine.repositories.Impl;

import org.BatiCuisine.config.DatabaseConnection;
import org.BatiCuisine.models.entities.Client;
import org.BatiCuisine.models.entities.Projet;
import org.BatiCuisine.models.enums.EtatProjet;
import org.BatiCuisine.repositories.Inter.ClientRepository;
import org.BatiCuisine.repositories.Inter.ProjetRepository;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProjetRepositoryImpl implements ProjetRepository {
    private final Connection connection;
    private final ClientRepository clientRepository;

    public ProjetRepositoryImpl(ClientRepository clientRepository) throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
        this.clientRepository = clientRepository;
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

    public void updateMargeBenef(int projetId, double margeBenef, double coutTotal) {
        String sql = "UPDATE projet SET marge_beneficiaire = ?, cout_total = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, margeBenef);
            stmt.setDouble(2, coutTotal);
            stmt.setInt(3, projetId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Map<Integer, Projet>> getAllProjects() {
        String sql = "SELECT * FROM projet ORDER BY id DESC ";
        Map<Integer, Projet> projectMap = new HashMap<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Projet project = mapResultSetToProject(rs);
                projectMap.put(project.getId(), project);
            }
            return Optional.of(projectMap);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Map<Integer, Projet> searchProjetByName(String name) throws SQLException {
        Map<Integer, Projet> matchingProjets = new HashMap<>();
        String sql = "SELECT * FROM projet WHERE nom_projet ILIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Projet projet = mapResultSetToProject(resultSet);
                    matchingProjets.put(projet.getId(), projet);
                }
            }
        }
        return matchingProjets;
    }


    public Projet mapResultSetToProject(ResultSet rs) throws SQLException {
        int clientId = rs.getInt("client_id");
        Client client = clientRepository.getClientById(clientId);

        Projet projet = new Projet(
                rs.getString("nom_projet"),
                rs.getDouble("marge_beneficiaire"),
                rs.getDouble("cout_total"),
                EtatProjet.valueOf(rs.getString("etat")),
                client,
                rs.getDouble("surface")
        );
        projet.setId(rs.getInt("id"));
        return projet;
    }
}


