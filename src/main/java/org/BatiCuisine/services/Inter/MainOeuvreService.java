package org.BatiCuisine.services.Inter;

import org.BatiCuisine.models.entities.MainOeuvre;

import java.sql.SQLException;

public interface MainOeuvreService {
    MainOeuvre ajouterMainOeuvre(MainOeuvre mainOeuvre) throws SQLException;
    boolean modifierMainOeuvreTva(int mainOeuvreId, double tva) throws SQLException;
}
