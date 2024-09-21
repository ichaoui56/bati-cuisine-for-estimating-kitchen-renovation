package org.BatiCuisine.services.Impl;

import org.BatiCuisine.models.entities.Projet;
import org.BatiCuisine.repositories.Inter.ProjetRepository;
import org.BatiCuisine.services.Inter.ProjetService;

import java.sql.SQLException;

public class ProjetServiceImpl implements ProjetService {
    private final ProjetRepository projetRepository;

    public ProjetServiceImpl(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    public Projet ajouterProjet(Projet projet) throws SQLException {
        return projetRepository.addProjet(projet);
    }

    public void modifierMargeBenef(int projetId, double margebenef) throws SQLException {
        projetRepository.updateMargeBenef(projetId, margebenef);
    }
}
