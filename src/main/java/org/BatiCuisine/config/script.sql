CREATE TYPE EtatProjet AS ENUM ('enCours', 'terminé', 'annulé');

CREATE TABLE Client (
                        id SERIAL PRIMARY KEY,
                        nom VARCHAR(255),
                        address VARCHAR(255),
                        phoneNumber VARCHAR(20),
                        estProfessionnel BOOLEAN
);

CREATE TABLE Projet (
                        id SERIAL PRIMARY KEY,
                        nomProjet VARCHAR(255),
                        margeBeneficiaire DOUBLE PRECISION,
                        coutTotal DOUBLE PRECISION,
                        surface DOUBLE PRECISION,
                        etat EtatProjet,
                        client_id INT,
                        FOREIGN KEY (client_id) REFERENCES Client(id)
);

-- Base table for shared attributes
CREATE TABLE Composant (
                               id SERIAL PRIMARY KEY,
                               nom VARCHAR(255),
                               tauxTVA DOUBLE PRECISION,
                               typeComposant VARCHAR(255),
                               projet_id INT,
                               FOREIGN KEY (projet_id) REFERENCES Projet(id)
);

-- Materiau inherits from BaseComposant
CREATE TABLE Material (
                          coutUnitaire DOUBLE PRECISION,
                          quantite DOUBLE PRECISION,
                          coutTransport DOUBLE PRECISION,
                          coefficientQualite DOUBLE PRECISION
) INHERITS (Composant);

-- MainOeuvre inherits from BaseComposant
CREATE TABLE MainOeuvre (
                            tauxHoraire DOUBLE PRECISION,
                            heuresTravail DOUBLE PRECISION,
                            productiviteOuvrier DOUBLE PRECISION
) INHERITS (Composant);

CREATE TABLE Devis (
                       id SERIAL PRIMARY KEY,
                       montantEstime DOUBLE PRECISION,
                       dateEmission DATE,
                       dateValidite DATE,
                       accepte BOOLEAN,
                       projet_id INT,
                       FOREIGN KEY (projet_id) REFERENCES Projet(id)
);
