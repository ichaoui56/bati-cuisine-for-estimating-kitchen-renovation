package org.BatiCuisine.services.Inter;

import org.BatiCuisine.models.entities.Projet;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public interface ProjetService {
    Projet ajouterProjet(Projet projet) throws SQLException;
    void modifierMargeBenef(int projetId, double margeBenef, double coutTotal) throws SQLException;
    Optional<Map<Integer, Projet>> fetchAllProjects() ;
    Map<Integer, Projet> searchProjetByName(String name) throws SQLException;
}
