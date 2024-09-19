package org.BatiCuisine.repositories.Inter;

import org.BatiCuisine.models.entities.MainOeuvre;

import java.sql.SQLException;

public interface MainOeuvreRepository {
    void addLabor(MainOeuvre mainOeuvre) throws SQLException;
}
