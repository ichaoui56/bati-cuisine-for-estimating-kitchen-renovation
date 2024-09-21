package org.BatiCuisine.repositories.Inter;

import org.BatiCuisine.models.entities.Material;

import java.sql.SQLException;

public interface MaterialRepository {
    Material addMaterial(Material material) throws SQLException;
    void updateMaterialTva(int materialId, double tva) throws SQLException;
}
