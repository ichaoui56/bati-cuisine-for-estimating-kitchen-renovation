package org.BatiCuisine.repositories.Inter;

import org.BatiCuisine.models.entities.Devis;

import java.util.Map;
import java.util.Optional;

public interface DevisRepository {
    void createDevis(Devis devis);
    Devis getDevisByProjetId(int projetId);
}
