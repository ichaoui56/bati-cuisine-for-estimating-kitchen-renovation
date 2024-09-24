package org.BatiCuisine.repositories.Impl;

import org.BatiCuisine.config.DatabaseConnection;
import org.BatiCuisine.models.entities.Client;
import org.BatiCuisine.repositories.Inter.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ClientRepositoryImpl implements ClientRepository {

    private final Connection connection;

    public ClientRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public Client addClient(Client client) throws SQLException {
        String query = "INSERT INTO client (nom, address, phone_number, est_professionnal) VALUES (?, ?, ?, ?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, client.getNom());
            pstmt.setString(2, client.getAddress());
            pstmt.setString(3, client.getPhoneNumber());
            pstmt.setBoolean(4, client.isEstProfessionnal());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    client.setId(id);
                    return client;
                } else {
                    throw new SQLException("Inserting client failed, no ID returned.");
                }
            }
        }
    }

    public Client getClientById(int clientId) throws SQLException {
        String sql = "SELECT * FROM client WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clientId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setNom(rs.getString("nom"));
                client.setAddress(rs.getString("address"));
                client.setPhoneNumber(rs.getString("phone_number"));
                client.setEstProfessionnal(rs.getBoolean("est_professionnal"));

                return client;
            } else {
                return null;
            }
        }
    }

    public Map<Integer, Client> searchClientByName(String name) throws SQLException {
        Map<Integer, Client> matchingClients = new HashMap<>();
        String sql = "SELECT * FROM client WHERE nom ILIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String adresse = resultSet.getString("address");
                    String numeroTelephone = resultSet.getString("phone_number");
                    boolean estProfessionnel = resultSet.getBoolean("est_professionnal");

                    Client client = new Client(nom, adresse, numeroTelephone, estProfessionnel);
                    client.setId(id);
                    matchingClients.put(id, client);
                }
            }
        }
        return matchingClients;
    }

}
