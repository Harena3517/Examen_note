-- -- =========================
-- -- INSERT CLIENT
-- -- =========================
-- INSERT INTO t_client (nom, prenom) VALUES
-- ('Rakoto', 'Jean'),
-- ('Rabe', 'Marie'),
-- ('Andry', 'Paul');

-- -- =========================
-- -- INSERT DEMANDE
-- -- =========================
-- INSERT INTO t_demande (date_demande, id_client, lieu, district) VALUES
-- ('2026-03-01', 1, 'Antananarivo', 'Analamanga'),
-- ('2026-03-05', 2, 'Mahajanga', 'Boeny'),
-- ('2026-03-10', 3, 'Fianarantsoa', 'Haute Matsiatra');

-- -- =========================
-- -- INSERT TYPE DEVIS
-- -- =========================
INSERT INTO t_typedevis (libelle) VALUES
('Devis etude'),
('Devis forage');

-- -- =========================
-- -- INSERT DEVIS
-- -- =========================
-- INSERT INTO t_devis (date_devis, id_demande, id_typedevis) VALUES
-- ('2026-03-02', 1, 1),
-- ('2026-03-06', 2, 1),
-- ('2026-03-11', 3, 2);

-- -- =========================
-- -- INSERT DETAIL DEVIS
-- -- =========================
-- INSERT INTO t_detail_devis (id_devis, libelle, montant, qtt) VALUES
-- (1, 'Etude du sol', 500000, 1),
-- (1, 'Analyse terrain', 300000, 1),
-- (2, 'Etude hydrologique', 450000, 1),
-- (3, 'Forage profond', 2000000, 1);

-- -- =========================
-- -- INSERT STATUS
-- -- =========================
INSERT INTO t_status (libelle) VALUES
('cree'),
('devis etude cree'),
('devis etude accepte'),
('devis etude refuse');

-- =========================
-- INSERT DEMANDE STATUS
-- =========================
-- INSERT INTO t_demande_status (id_demande, id_status, date_status) VALUES
-- (1, 1, '2026-03-01'), -- cree
-- (1, 2, '2026-03-02'), -- devis etude cree
-- (1, 3, '2026-03-03'), -- accepte

-- (2, 1, '2026-03-05'),
-- (2, 2, '2026-03-06'),
-- (2, 4, '2026-03-07'), -- refuse

-- (3, 1, '2026-03-10'),
-- (3, 2, '2026-03-11');