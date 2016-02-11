-- MySQL
-- DROP-setninger i tilfelle vi må kjøre scriptet på nytt.

DROP TABLE leilighet;
DROP TABLE andelseier;
DROP TABLE bygning;
DROP TABLE borettslag;
DROP TABLE poststed;

-- Lager tabellene, legger inn NOT NULL- og UNIQUE-krav der det er naturlig
-- Vær forsiktig med å legge inn slike krav, det er vanskelig å forandre
-- dem i ettertid.

CREATE TABLE poststed(
  postnr SMALLINT,
  poststed VARCHAR(20) NOT NULL,
  CONSTRAINT poststed_pk PRIMARY KEY(postnr));

CREATE TABLE borettslag(
  bolag_navn VARCHAR(20),
  bolag_adr VARCHAR(40) NOT NULL UNIQUE,
  etabl_aar SMALLINT NOT NULL,
  postnr SMALLINT NOT NULL,
  CONSTRAINT borettslag_pk PRIMARY KEY(bolag_navn));

CREATE TABLE bygning(
  bygn_id INTEGER NOT NULL AUTO_INCREMENT,
  bygn_adr VARCHAR(40) NOT NULL,
  ant_etasjer INTEGER  DEFAULT 1,
  bolag_navn VARCHAR(20) NOT NULL,
  postnr SMALLINT NOT NULL,
  CONSTRAINT bygning_pk PRIMARY KEY(bygn_id));

CREATE TABLE leilighet(
  leil_nr INTEGER NOT NULL AUTO_INCREMENT,
  ant_rom SMALLINT NOT NULL,
  ant_kvm REAL NOT NULL,
  etasje SMALLINT DEFAULT 1,
  bygn_id INTEGER NOT NULL,
  and_eier_nr INTEGER NOT NULL UNIQUE,
  CONSTRAINT leilighet_pk PRIMARY KEY(leil_nr));

CREATE TABLE andelseier(
  and_eier_nr INTEGER NOT NULL AUTO_INCREMENT,
  fornavn VARCHAR(30) NOT NULL,
  etternavn VARCHAR(30) NOT NULL,
  telefon CHAR(15),
  ansiennitet SMALLINT,
  bolag_navn VARCHAR(20) NOT NULL,
  CONSTRAINT andelseier_pk PRIMARY KEY(and_eier_nr));

-- Fremmednøkler

ALTER TABLE borettslag
  ADD CONSTRAINT borettslag_fk1 FOREIGN KEY(postnr)
  REFERENCES poststed(postnr);

ALTER TABLE bygning
  ADD CONSTRAINT bygning_fk1 FOREIGN KEY(postnr)
  REFERENCES poststed(postnr);

ALTER TABLE bygning
  ADD CONSTRAINT bygning_fk2 FOREIGN KEY(bolag_navn)
  REFERENCES borettslag(bolag_navn);

ALTER TABLE leilighet
  ADD CONSTRAINT leilighet_fk1 FOREIGN KEY(bygn_id)
  REFERENCES bygning(bygn_id);

ALTER TABLE leilighet
  ADD CONSTRAINT leilighet_fk2 FOREIGN KEY(and_eier_nr)
  REFERENCES andelseier(and_eier_nr);

ALTER TABLE andelseier
  ADD CONSTRAINT andelseier_fk2 FOREIGN KEY(bolag_navn)
  REFERENCES borettslag(bolag_navn);



-- Legger inn gyldige data

INSERT INTO poststed(postnr, poststed) VALUES(2020, 'Skedsmokorset');
INSERT INTO poststed(postnr, poststed) VALUES(6408, 'Aureosen');
INSERT INTO poststed(postnr, poststed) VALUES(7033, 'Trondheim');
INSERT INTO poststed(postnr, poststed) VALUES(7020, 'Trondheim');

INSERT INTO borettslag(bolag_navn, bolag_adr, etabl_aar, postnr) VALUES('Tertitten', 'Åsveien 100', 1980, 7020);
INSERT INTO borettslag(bolag_navn, bolag_adr, etabl_aar, postnr) VALUES('Sisiken', 'Brurød', 1990, 7033);
INSERT INTO borettslag(bolag_navn, bolag_adr, etabl_aar, postnr) VALUES('Lerken', 'Storgt 5', 2000, 6408);

INSERT INTO andelseier(and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
                        VALUES(DEFAULT, 'Even', 'Trulsbo', '56667743', 3, 'Tertitten');
INSERT INTO andelseier(and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
                        VALUES(DEFAULT, 'Anna', 'Olsen', '45674588', 10, 'Tertitten');
INSERT INTO andelseier(and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
                        VALUES(DEFAULT, 'Ingrid', 'Olsen', '45785388', 8, 'Tertitten');
INSERT INTO andelseier(and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
                        VALUES(DEFAULT, 'Arne', 'Torp', '78565388', 7, 'Tertitten');
INSERT INTO andelseier(and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
                        VALUES(DEFAULT, 'Arne', 'Martinsen', '78555388', 4, 'Sisiken');

INSERT INTO bygning(bygn_id, bygn_adr, ant_etasjer, bolag_navn, postnr) VALUES(DEFAULT, 'Åsveien 100a', 3, 'Tertitten', 7020);
INSERT INTO bygning(bygn_id, bygn_adr, ant_etasjer, bolag_navn, postnr) VALUES(DEFAULT, 'Åsveien 100b', 3, 'Tertitten', 7020);
INSERT INTO bygning(bygn_id, bygn_adr, ant_etasjer, bolag_navn, postnr) VALUES(DEFAULT, 'Åsveien 100c', 6, 'Tertitten', 7020);
INSERT INTO bygning(bygn_id, bygn_adr, ant_etasjer, bolag_navn, postnr) VALUES(DEFAULT, 'Storgt 10', 2, 'Sisiken', 7020);

-- bruker defaultverdien for antall etasjer
INSERT INTO bygning(bygn_id, bygn_adr, bolag_navn, postnr) VALUES(DEFAULT, 'Åsveien 100', 'Tertitten', 7020);

INSERT INTO leilighet(leil_nr, ant_rom, ant_kvm, etasje, bygn_id, and_eier_nr) VALUES(DEFAULT, 5, 110, 3, 1, 1);
INSERT INTO leilighet(leil_nr, ant_rom, ant_kvm, etasje, bygn_id, and_eier_nr) VALUES(DEFAULT, 5, 110, 3, 1, 2);
INSERT INTO leilighet(leil_nr, ant_rom, ant_kvm, etasje, bygn_id, and_eier_nr) VALUES(DEFAULT, 2, 50, 1, 3, 3);

-- bruker defaultverdien for etasje
INSERT INTO leilighet(leil_nr, ant_rom, ant_kvm, bygn_id, and_eier_nr) VALUES(DEFAULT, 5, 110, 1, 4);






-- for å gjøre bedre testing
INSERT INTO borettslag(bolag_navn, bolag_adr, etabl_aar, postnr) VALUES('Borl1', 'Sverdrupsvei 1337', 1975, 7020);
INSERT INTO borettslag(bolag_navn, bolag_adr, etabl_aar, postnr) VALUES('Borl2', 'Kongens gt. 10', 1980, 7020);
INSERT INTO borettslag(bolag_navn, bolag_adr, etabl_aar, postnr) VALUES('BOrtest', 'testgt 20', 1985, 7020);






--Oppgave
--1) Finn alle borettslag etablert i årene 1975-1985.
SELECT * FROM borettslag WHERE etabl_aar BETWEEN 1975 AND 1985;
-- Sortert etter etableringsår:
SELECT * FROM borettslag WHERE etabl_aar BETWEEN 1975 AND 1985 ORDER BY etabl_aar;

--2)
/*
Skriv ut en liste over andelseiere. Listen skal ha linjer som ser slik ut (tekster i kursiv er data fra databasen):
"fornavn etternavn, ansiennitet: ansiennitet år".
Listen skal være sortert på ansiennitet, de med lengst ansiennitet øverst.
 */
SELECT fornavn, etternavn, ansiennitet FROM andelseier ORDER BY ansiennitet desc;

--3) I hvilket år ble det eldste borettslaget etablert?
----------SELECT MIN(borettslag.etabl_aar) FROM borettslag;
--med bedre forklaring i tilbakemeldingen
SELECT MIN(etabl_aar) AS År FROM borettslag;

--4) Finn adressene til alle bygninger som inneholder leiligheter med minst tre rom.
SELECT bygning.bygn_adr FROM bygning NATURAL JOIN leilighet WHERE leilighet.ant_rom > 2 GROUP BY bygning.bygn_adr;

--5) Finn antall bygninger i borettslaget "Tertitten".
SELECT COUNT(*) FROM bygning WHERE bygning.bolag_navn = "Tertitten";

--6)
/*Lag en liste som viser antall bygninger i hvert enkelt borettslag.
Listen skal være sortert på borettslagsnavn.
Husk at det kan finnes borettslag uten bygninger - de skal også med.
 */
SELECT COUNT(*), borettslag.bolag_navn FROM bygning RIGHT JOIN borettslag ON (bygning.bolag_navn = borettslag.bolag_navn) GROUP BY borettslag.bolag_navn;

--7) Finn antall leiligheter i borettslaget "Tertitten".
SELECT COUNT(*) AS antall, bolag_navn FROM leilighet NATURAL JOIN bygning WHERE bolag_navn = "Tertitten";

--8) Hvor høyt kan du bo i borettslaget "Tertitten"?
SELECT MAX(etasje) FROM leilighet NATURAL JOIN bygning NATURAL JOIN borettslag WHERE borettslag.bolag_navn = "Tertitten";

--9) Finn navn og nummer til andelseiere som ikke har leilighet.
SELECT fornavn, etternavn FROM andelseier WHERE and_eier_nr NOT IN(SELECT and_eier_nr FROM andelseier NATURAL JOIN leilighet);

--10)
/* Finn antall andelseiere pr borettslag, sortert etter antallet.
Husk at det kan finnes borettslag uten andelseiere - de skal også med.
 */
SELECT COUNT(and_eier_nr) AS antall, borettslag.bolag_navn FROM andelseier RIGHT JOIN borettslag ON (borettslag.bolag_navn = andelseier.bolag_navn) GROUP BY bolag_navn ORDER BY antall;


--11)
/*
Skriv ut en liste over alle andelseiere. For de som har leilighet, skal leilighetsnummeret skrives ut.
 */
SELECT * FROM andelseier LEFT JOIN leilighet ON(andelseier.and_eier_nr = leilighet.and_eier_nr);

--12)
/*
Hvilke borettslag har leiligheter med eksakt 4 rom?
 */
SELECT bolag_navn FROM borettslag NATURAL JOIN bygning NATURAL JOIN leilighet WHERE leilighet.ant_rom = 4 GROUP BY borettslag.bolag_navn;

--13)
/*
Skriv ut en liste over antall andelseiere pr postnr og poststed, begrenset til de som bor i leiligheter tilknyttet et borettslag.
Husk at postnummeret til disse er postnummeret til bygningen de bor i, og ikke postnummeret til borettslaget.
Du trenger ikke ta med poststeder med 0 andelseiere.
(Ekstraoppgave: Hva hvis vi vil ha med poststeder med 0 andelseiere?)
 */
SELECT COUNT(and_eier_nr) AS antall_andelseiere, poststed.postnr, poststed.poststed FROM andelseier NATURAL JOIN bygning NATURAL JOIN leilighet NATURAL JOIN poststed;
