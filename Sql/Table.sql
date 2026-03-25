CREATE DATABASE forage_db;

\c forage_db;

CREATE TABLE t_client (
    id_client SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL
);

CREATE TABLE t_demande (
    id_demande SERIAL PRIMARY KEY,
    date_demande TIMESTAMP NOT NULL,
    id_client INT NOT NULL,
    lieu VARCHAR(150),
    district VARCHAR(100),

    FOREIGN KEY (id_client) REFERENCES t_client(id_client)
);

CREATE TABLE t_typedevis (
    id_typedevis SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE t_devis (
    id_devis SERIAL PRIMARY KEY,
    date_devis TIMESTAMP NOT NULL,
    id_demande INT NOT NULL,
    id_typedevis INT NOT NULL,

    FOREIGN KEY (id_demande) REFERENCES t_demande(id_demande),
    FOREIGN KEY (id_typedevis) REFERENCES t_typedevis(id_typedevis)
);

CREATE TABLE t_detail_devis (
    id_detail SERIAL PRIMARY KEY,
    id_devis INT NOT NULL,
    libelle VARCHAR(150),
    montant NUMERIC(12,2),
    qtt INT,

    FOREIGN KEY (id_devis) REFERENCES t_devis(id_devis),

    CONSTRAINT check_qtt CHECK (qtt > 0)
);

CREATE TABLE t_status (
    id_status SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);


CREATE TABLE t_demande_status (
    id_demande_status SERIAL PRIMARY KEY,
    id_demande INT NOT NULL,
    id_status INT NOT NULL,
    date_status TIMESTAMP NOT NULL,

    FOREIGN KEY (id_demande) REFERENCES t_demande(id_demande),
    FOREIGN KEY (id_status) REFERENCES t_status(id_status)
);

