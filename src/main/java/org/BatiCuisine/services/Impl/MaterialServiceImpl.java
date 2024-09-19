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

    public boolean ajouterMaterial(Material material) {
        materialRepository.addMaterial(material);
        return true;
    }
}
