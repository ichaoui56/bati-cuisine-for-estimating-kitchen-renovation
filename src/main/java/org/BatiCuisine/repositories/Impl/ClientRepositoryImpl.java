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


}
