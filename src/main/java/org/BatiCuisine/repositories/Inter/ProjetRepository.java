package org.BatiCuisine.repositories.Inter;

import org.BatiCuisine.models.entities.Projet;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public interface ProjetRepository{
    Projet addProjet(Projet projet) throws SQLException;
    void updateMargeBenef(int id, double margeBenef, double coutTotal) throws SQLException;
    Optional<Map<Integer, Projet>> getAllProjects();
}
