package org.BatiCuisine.repositories.Inter;

import org.BatiCuisine.models.entities.Projet;

import java.sql.SQLException;

public interface ProjetRepository{
    Projet addProjet(Projet projet) throws SQLException;
    void updateMargeBenef(int id, double margeBenef) throws SQLException;
}
