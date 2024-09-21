package org.BatiCuisine.services.Impl;

import org.BatiCuisine.models.entities.Material;
import org.BatiCuisine.repositories.Inter.MaterialRepository;
import org.BatiCuisine.services.Inter.MaterialService;

import java.sql.SQLException;

public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;

    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Material ajouterMaterial(Material material) throws SQLException {
        return materialRepository.addMaterial(material);
    }

    public boolean modifierMaterialTva(int materialId, double tva) throws SQLException {
        materialRepository.updateMaterialTva(materialId,tva);
        return true;
    }

}
