package org.BatiCuisine.repositories.Impl;

import org.BatiCuisine.config.DatabaseConnection;
import org.BatiCuisine.models.entities.Client;
import org.BatiCuisine.repositories.Inter.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public Client getClientById(int id) throws SQLException {
        String query = "SELECT * FROM client WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Client client = new Client();
                    client.setId(rs.getInt("id"));
                    client.setNom(rs.getString("nom"));
                    client.setAddress(rs.getString("address"));
                    client.setPhoneNumber(rs.getString("phone_number"));
                    client.setEstProfessionnal(rs.getBoolean("est_professionnal"));
                    return client;
                } else {
                    throw new SQLException("Client with ID " + id + " not found.");
                }
            }
        }
    }

}
