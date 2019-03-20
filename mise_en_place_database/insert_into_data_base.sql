    -- ==============================
-- SGBD     :   mariadb 10.1.23
-- fichier	:	pts_ddl.sql
-- base		:	pts
-- auteur	: 	Pierre Lemaigre
-- date		:	19/06/2018
-- role		:  Insertion de nuplet tests pour la base de donnees
-- projet	: 	pts
-- Resultat dans: pts.*
-- ==============================


--===============================
--===============================
-- Table utilisateur
--
-- Cas valide n°1: un utilisateur s'inscrit avec les valeurs suivantes.
INSERT INTO utilisateur(mail,login,mot_de_passe) VALUES("pierre@mail.com","a","0CC175B9C0F1B6A831C399E269772661");
-- Cas valide n°2: un utilisateur s'incrit avec les valeurs suivantes.
INSERT INTO utilisateur(mail,login,mot_de_passe,admin) VALUES("clement@mail.com","u","7B774EFFE4A349C6DD82AD4F4F21D34C",true);

-- Cas invalide n°1: un utilisateur s'inscrit avec la meme adresse mail d'un autre utilisateur
INSERT INTO utilisateur(mail,login,mot_de_passe) VALUES("pierre@mail.com","b","92EB5FFEE6AE2FEC3AD71C777531578F");
-- Cas invalide n°2: un utilisateur s'inscrit sans mot de passe:
INSERT INTO utilisateur(mail,login) VALUES("pierre@mail.com","b");
-- Cas invalide n°3: un utilisateur s'inscrit sans login:
INSERT INTO utilisateur(mail,mot_de_passe) VALUES("pierre@mail.com","92EB5FFEE6AE2FEC3AD71C777531578F");
-- Cas invalide n°4: un utilisateur s'inscrit sans adresse mail:
INSERT INTO utilisateur(login,mot_de_passe) VALUES("u","92EB5FFEE6AE2FEC3AD71C777531578F");
--===============================
--===============================


--===============================
--===============================
INSERT INTO jour(this_date,numero_jour) VALUES(STR_TO_DATE('18/06/2018','%d/%m/%Y'),1);
INSERT INTO jour(this_date,numero_jour) VALUES(STR_TO_DATE('19/06/2018','%d/%m/%Y'),2);
INSERT INTO jour(this_date,numero_jour) VALUES(STR_TO_DATE('20/06/2018','%d/%m/%Y'),3);
INSERT INTO jour(this_date,numero_jour) VALUES(STR_TO_DATE('21/06/2018','%d/%m/%Y'),4);
INSERT INTO jour(this_date,numero_jour) VALUES(STR_TO_DATE('22/06/2018','%d/%m/%Y'),5);
INSERT INTO jour(this_date,numero_jour) VALUES(STR_TO_DATE('23/06/2018','%d/%m/%Y'),6);
INSERT INTO jour(this_date,numero_jour) VALUES(STR_TO_DATE('24/06/2018','%d/%m/%Y'),7);


--===============================
--===============================
INSERT INTO question(question,indice,id_jour) VALUES("Quelle est la couleur du cheval blanc d'henry 4?","Reflechissez",1);




SELECT id,question,indice,annee_tc,nb_bonnes_reponses,nb_mauvaises_reponses FROM question q, jour j WHERE q.id_jour=j.id AND j.date=STR_TO_DATE('20/06/2018','%d/%m/%Y');
