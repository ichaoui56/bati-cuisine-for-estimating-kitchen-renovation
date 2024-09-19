package org.BatiCuisine.services.Inter;

import org.BatiCuisine.models.entities.MainOeuvre;

import java.sql.SQLException;

public interface MainOeuvreService {
    boolean ajouterMainOeuvre(MainOeuvre mainOeuvre) throws SQLException;
}
