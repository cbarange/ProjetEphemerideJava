-- ==============================
-- SGBD     :   mariadb 10.1.23
-- fichier	:	creation_user.sql
-- base		:	pts
-- auteur	: 	Pierre Lemaigre
-- date		:	19/06/2018
-- role		:	Mise en place de deux utilisateurs pour la base de donnees
-- projet	: 	pts
-- Resultat dans: mysql.user
-- ==============================

CREATE OR REPLACE USER 'client_select'@'%'
    IDENTIFIED BY 'B84AMW48';

GRANT SELECT ON pts.* TO 'client_select'@'%';

CREATE OR REPLACE USER 'client_insert'@'%'
    IDENTIFIED BY '4N84HVF6';

GRANT INSERT ON pts.* TO 'client_insert'@'%';

CREATE OR REPLACE USER 'client_update'@'%'
    IDENTIFIED BY '72H4RW4D';

GRANT UPDATE ON pts.* TO 'client_update'@'%';

CREATE OR REPLACE USER 'client_delete'@'%'
    IDENTIFIED BY '8ZC4MP73';

GRANT DELETE ON pst.* TO 'client_delete'@'%';
