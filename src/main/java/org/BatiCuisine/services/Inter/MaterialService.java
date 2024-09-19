package org.BatiCuisine.services.Inter;

import org.BatiCuisine.models.entities.Material;

import java.sql.SQLException;

public interface MaterialService {
    boolean ajouterMaterial(Material material) throws SQLException;
}
