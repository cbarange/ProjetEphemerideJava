-- ==============================
-- SGBD     :   mariadb 10.1.23
-- fichier	:	pts_dr.sql
-- base		:	pts
-- auteur	: 	Pierre Lemaigre
-- date		:	20/06/2018
-- role		:   Création des dépendances de références
-- projet	: 	pts
-- Resultat dans: pts.*
-- ==============================

ALTER TABLE evenement
    ADD CONSTRAINT FOREIGN KEY (id_jour) REFERENCES jour(id);

ALTER TABLE reponse
    ADD CONSTRAINT FOREIGN KEY (id_question) REFERENCES question(id);

ALTER TABLE question
    ADD CONSTRAINT FOREIGN KEY (id_jour) REFERENCES jour(id);

ALTER TABLE reponse_eleve
    ADD CONSTRAINT FOREIGN KEY (id_question) REFERENCES question(id);

ALTER TABLE reponse_eleve
    ADD CONSTRAINT FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id);

ALTER TABLE jour
    ADD CONSTRAINT FOREIGN KEY (numero_jour) REFERENCES configuration(id_numero_jour);
