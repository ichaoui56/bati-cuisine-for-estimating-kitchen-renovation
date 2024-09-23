package org.BatiCuisine.services.Impl;

import org.BatiCuisine.models.entities.Devis;
import org.BatiCuisine.repositories.Inter.DevisRepository;
import org.BatiCuisine.services.Inter.DevisService;

import java.sql.SQLException;

public class DevisServiceImpl implements DevisService {
    private final DevisRepository devisRepository;

    public DevisServiceImpl(DevisRepository devisRepository) {
        this.devisRepository = devisRepository;
    }

    public boolean ajouterDevis(Devis devis) {
        this.devisRepository.createDevis(devis);
        return true;
    }
}
