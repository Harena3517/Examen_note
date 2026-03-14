INSERT INTO t_candidat (id, nom, prenom, matricule) VALUES
(1,'Candidat1','Jean','MAT001'),
(2,'Candidat2','Marie','MAT002');


INSERT INTO t_correcteur (id, nom, prenom) VALUES
(1,'Correcteur1','Andry'),
(2,'Correcteur2','Hery'),
(3,'Correcteur3','Ony');


INSERT INTO t_matiere (id, nom) VALUES
(1,'JAVA'),
(2,'PHP');


INSERT INTO t_resolution (id, nom) VALUES
(1,'Petit'),
(2,'Grand'),
(3,'Moyenne');


INSERT INTO t_operateur (id, operateur) VALUES
(1,'<'),
(2,'<='),
(3,'>'),
(4, '>=');



INSERT INTO t_parametre (id,id_matiere,id_resolution,id_operateur,seuil) VALUES
(1,1,2,2,3),
(2,1,3,4,3),
(3,2,1,2,4),
(4,2,2,3,4);


INSERT INTO t_note (id_candidat,id_matiere,id_correcteur,note) VALUES
(1,1,1,13),
(1,1,2,12),
(1,1,3,8),
(1,2,1,12),
(1,2,2,10),
(1,2,3,16),
(2,1,1,12),
(2,1,2,10),
(2,1,1,15),
(2,2,2,13);
