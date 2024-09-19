package org.BatiCuisine.repositories.Inter;

import org.BatiCuisine.models.entities.Material;

import java.sql.SQLException;

public interface MaterialRepository {
    void addMaterial(Material material) throws SQLException;
}
