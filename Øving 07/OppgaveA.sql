DROP TABLE IF EXISTS borettslag;
DROP TABLE IF EXISTS bygning;
DROP TABLE IF EXISTS andelseier;
DROP TABLE IF EXISTS leilighet;

CREATE TABLE borettslag(
  bor_id INTEGER AUTO_INCREMENT,
  navn VARCHAR(50) NOT NULL,
  adresse VARCHAR(50) NOT NULL,
  antHus INTEGER,
  etableringsAar INTEGER(4) NOT NULL,
  PRIMARY KEY(bor_id)
);

CREATE TABLE bygning(
  bygn_id INTEGER AUTO_INCREMENT,
  etasjer INTEGER NOT NULL,
  leiligheter INTEGER NOT NULL,
  bor_id INTEGER NOT NULL,
  PRIMARY KEY(bygn_id),
  FOREIGN KEY(bor_id) REFERENCES borettslag(bor_id)
);

CREATE TABLE leilighet(
  leilighets_id INTEGER AUTO_INCREMENT,
  antRom INTEGER NOT NULL,
  antM2 INTEGER NOT NULL,
  etasje INTEGER NOT NULL,
  leilighetsNr INTEGER NOT NULL,
  PRIMARY KEY(leilighets_id),
  bygn_id INTEGER NOT NULL,
  FOREIGN KEY(bygn_id) REFERENCES bygning(bygn_id)
);

CREATE TABLE andelseier(
  andeier_id INTEGER AUTO_INCREMENT,
  navn VARCHAR(50) NOT NULL,
  leilighets_id INTEGER,
  PRIMARY KEY(andeier_id),
  FOREIGN KEY(leilighets_id) REFERENCES leilighet(leilighets_id)
);