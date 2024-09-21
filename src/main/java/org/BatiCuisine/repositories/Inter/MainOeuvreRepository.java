package org.BatiCuisine.repositories.Inter;

import org.BatiCuisine.models.entities.MainOeuvre;

import java.sql.SQLException;

public interface MainOeuvreRepository {
    MainOeuvre addLabor(MainOeuvre mainOeuvre) throws SQLException;
    void updateMainOeuvreTva(int mainOeuvreId, double tva) throws SQLException;
}
