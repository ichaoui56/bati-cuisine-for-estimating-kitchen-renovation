package org.BatiCuisine.services.Inter;

import org.BatiCuisine.models.entities.Projet;

import java.sql.SQLException;

public interface ProjetService {
    Projet ajouterProjet(Projet projet) throws SQLException;
    void modifierMargeBenef(int projetId, double margeBenef) throws SQLException;
}
