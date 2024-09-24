package org.BatiCuisine.services.Inter;

import org.BatiCuisine.models.entities.Devis;
import java.util.Map;
import java.util.Optional;

public interface DevisService {
    boolean ajouterDevis(Devis devis);
    Devis getDevisByProjetId(int projetId);
}
