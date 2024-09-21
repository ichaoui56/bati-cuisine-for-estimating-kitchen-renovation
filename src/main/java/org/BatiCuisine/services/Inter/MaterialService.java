package org.BatiCuisine.services.Inter;

import org.BatiCuisine.models.entities.Material;

import java.sql.SQLException;

public interface MaterialService {
    Material ajouterMaterial(Material material) throws SQLException;
    boolean modifierMaterialTva(int materialId, double tva) throws SQLException;
}
