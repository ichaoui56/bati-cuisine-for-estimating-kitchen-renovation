package org.BatiCuisine.services.Impl;

import org.BatiCuisine.models.entities.MainOeuvre;
import org.BatiCuisine.repositories.Inter.MainOeuvreRepository;
import org.BatiCuisine.services.Inter.MainOeuvreService;

import java.sql.SQLException;

public class MainOeuvreServiceImpl implements MainOeuvreService {
    private final MainOeuvreRepository mainOeuvreRepository;

    public MainOeuvreServiceImpl(MainOeuvreRepository mainOeuvre) {
        this.mainOeuvreRepository = mainOeuvre;
    }

    public boolean ajouterMainOeuvre(MainOeuvre mainOeuvre) throws SQLException {
        mainOeuvreRepository.addLabor(mainOeuvre);
        return true;
    }
}
