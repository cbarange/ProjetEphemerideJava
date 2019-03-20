-- ==============================
-- SGBD     :   mariadb 10.1.23
-- fichier	:	pts_ddl.sql
-- base		:	pts
-- auteur	: 	Pierre Lemaigre
-- date		:	19/06/2018
-- role		:   Création des tables dans la database pts
-- projet	: 	pts
-- Resultat dans: pts.*
-- ==============================

CREATE OR REPLACE TABLE utilisateur(
    id INTEGER AUTO_INCREMENT,
    mail VARCHAR(50) NOT NULL,
    login VARCHAR(16) NOT NULL,
    mot_de_passe VARCHAR(32) NOT NULL,
    points INT NULL DEFAULT 5,
    admin BOOLEAN DEFAULT false,
    millestone VARCHAR(1) NULL DEFAULT "0",
    theme VARCHAR(1) NULL DEFAULT "1",
    last_connexion DATE NULL,
    CONSTRAINT PK_UTILISATEUR PRIMARY KEY (id),
    CONSTRAINT UQ_MAIL UNIQUE (mail)
);

CREATE OR REPLACE TABLE admin_key(
    id SMALLINT NOT NULL DEFAULT 1,
    clee VARCHAR(32) NOT NULL DEFAULT "685F64D327F1676798DCA1B01E90EEC1",
    CONSTRAINT PK_ADMIN_KEY PRIMARY KEY (id),
    CONSTRAINT UQ_CLEE UNIQUE (clee)
);

CREATE OR REPLACE TABLE configuration(
    id_numero_jour SMALLINT NOT NULL,
    nom_jour VARCHAR(8) NOT NULL,
    etat BOOLEAN DEFAULT true,
    CONSTRAINT PK_CONFIGURATION PRIMARY KEY (id_numero_jour),
    CONSTRAINT UQ_NOM_JOUR UNIQUE (nom_jour)
);

CREATE OR REPLACE TABLE jour(
    id INTEGER AUTO_INCREMENT,
    this_date DATE NOT NULL,
    numero_jour SMALLINT NOT NULL,
    CONSTRAINT PK_JOUR PRIMARY KEY (id),
    CONSTRAINT UQ_DATE UNIQUE (this_date)
);

CREATE OR REPLACE TABLE question(
    id INTEGER AUTO_INCREMENT,
    question VARCHAR(500) NOT NULL,
    indice VARCHAR(140) NOT NULL,
    annee_tc VARCHAR(3) NOT NULL DEFAULT "TC1",
    nb_bonnes_reponses INT NULL DEFAULT 0,
    nb_mauvaises_reponses INT NULL DEFAULT 0,
    id_jour INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT PK_QUESTION PRIMARY KEY (id),
    CONSTRAINT UQ_ID_JOUR UNIQUE (id_jour)
);

CREATE OR REPLACE TABLE evenement(
    id INTEGER AUTO_INCREMENT,
    contenu VARCHAR(500) NOT NULL,
    lieu VARCHAR(140) NOT NULL,
    type_evenement VARCHAR(10) NOT NULL DEFAULT "Loisirs",
    id_jour INTEGER NOT NULL,
    CONSTRAINT PK_EVENEMENT PRIMARY KEY (id)
);

CREATE OR REPLACE TABLE reponse(
    id INTEGER AUTO_INCREMENT,
    est_bonne BOOLEAN NOT NULL DEFAULT false,
    contenu VARCHAR(500) NOT NULL,
    id_question INTEGER NOT NULL,
    CONSTRAINT PK_REPONSE PRIMARY KEY (id)
);

CREATE OR REPLACE TABLE reponse_eleve(
    id INTEGER AUTO_INCREMENT,
    est_bonne BOOLEAN NOT NULL DEFAULT false,
    id_question INTEGER NOT NULL,
    id_user INTEGER NOT NULL,
    CONSTRAINT PK_REPONSES_ELEVES PRIMARY KEY (id),
    CONSTRAINT UQ_ID_QUESTION UNIQUE (id_question,id_user)
);
