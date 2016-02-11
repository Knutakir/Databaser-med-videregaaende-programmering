DROP TABLE IF EXISTS Vikartjeneste;
DROP TABLE IF EXISTS Oppdrag;
DROP TABLE IF EXISTS Kand_kval;
DROP TABLE IF EXISTS Kvalifikasjon;
DROP TABLE IF EXISTS Bedrift;
DROP TABLE IF EXISTS Kandidat;

CREATE TABLE Kandidat(
  kandidat_id INTEGER AUTO_INCREMENT NOT NULL,
  fornavn VARCHAR(50) NOT NULL,
  etternavn VARCHAR(50) NOT NULL,
  telefon CHAR(15) NOT NULL,
  epost_adresse VARCHAR(50) NOT NULL,
  PRIMARY KEY(kandidat_id)
);

CREATE TABLE Bedrift(
  org_nr INTEGER NOT NULL,
  navn VARCHAR(50) NOT NULL,
  telefon CHAR(8) NOT NULL,
  epost_adresse VARCHAR(50) NOT NULL,
  PRIMARY KEY(org_nr)
);

CREATE TABLE Kvalifikasjon(
  kval_id INTEGER AUTO_INCREMENT NOT NULL,
  beskrivelse VARCHAR(50) NOT NULL,
  PRIMARY KEY(kval_id)
);

CREATE TABLE Kand_kval(
  kandidat_id INTEGER NOT NULL,
  kval_id INTEGER NOT NULL,
  PRIMARY KEY(kandidat_id, kval_id),
  FOREIGN KEY(kandidat_id) REFERENCES Kandidat(kandidat_id),
  FOREIGN KEY(kval_id) REFERENCES Kvalifikasjon(kval_id)
);

CREATE TABLE Oppdrag(
  oppdr_id INTEGER AUTO_INCREMENT NOT NULL,
  kval_id INTEGER,
  org_nr INTEGER NOT NULL,
  kandidat_id INTEGER,
  startdato DATE NOT NULL,
  sluttdato DATE NOT NULL,
  ant_timer INTEGER NOT NULL,
  faktisk_start DATE,
  faktisk_slutt DATE,
  faktisk_ant_timer INTEGER,
  PRIMARY KEY(oppdr_id),
  FOREIGN KEY(org_nr) REFERENCES Bedrift(org_nr),
  FOREIGN KEY(kandidat_id) REFERENCES Kandidat(kandidat_id),
  FOREIGN KEY(kval_id) REFERENCES Kvalifikasjon(kval_id)
);

CREATE TABLE Vikartjeneste(
  org_nr INTEGER NOT NULL,
  navn VARCHAR(50) NOT NULL,
  adresse VARCHAR(50) NOT NULL,
  telefon CHAR(15) NOT NULL,
  epost VARCHAR(50) NOT NULL,
  PRIMARY KEY(org_nr)
);

INSERT INTO Kandidat(kandidat_id, fornavn, etternavn, telefon, epost_adresse) VALUES(DEFAULT, 'Knut', 'Kirkhorn', 91007061, 'knut@knutmail.com');
INSERT INTO Kandidat(Kandidat_id, fornavn, etternavn, telefon, epost_adresse) VALUES(DEFAULT, 'Ingunn', 'Sund', 110, 'ingunns@online.no');
INSERT INTO Kandidat(kandidat_id, fornavn, etternavn, telefon, epost_adresse) VALUES(DEFAULT, 'Ola', 'Nordmann', 12312, 'asdasd@asd.com');

INSERT INTO Bedrift(org_nr, navn, telefon, epost_adresse) VALUES(1233, 'Peppes Pizza', 12344, 'peppes@piz.za');
INSERT INTO Bedrift(org_nr, navn, telefon, epost_adresse) VALUES(1234, 'Trondheim Catering', 12345, 'trond@heim.cat');
INSERT INTO Bedrift(org_nr, navn, telefon, epost_adresse) VALUES(1235, 'The Brogrammers', 123455, 'bro@gramm.ers');

INSERT INTO Kvalifikasjon(kval_id, beskrivelse) VALUES(DEFAULT, 'Økonomi');
INSERT INTO Kvalifikasjon(kval_id, beskrivelse) VALUES(DEFAULT, 'Programmering');
INSERT INTO Kvalifikasjon(kval_id, beskrivelse) VALUES(DEFAULT, 'Ledelse');

INSERT INTO Kand_kval(kandidat_id, kval_id) VALUES(1, 1);
INSERT INTO Kand_kval(kandidat_id, kval_id) VALUES(2, 2);
INSERT INTO Kand_kval(kandidat_id, kval_id) VALUES(3, 3);

INSERT INTO Oppdrag(oppdr_id, kval_id, org_nr, kandidat_id, startdato, sluttdato, ant_timer, faktisk_start, faktisk_slutt, faktisk_ant_timer) VALUES(DEFAULT, 1, 1233, NULL, DATE('2014-11-22'), DATE('2016-11-22'), 10, NULL, NULL, NULL);
INSERT INTO Oppdrag(oppdr_id, kval_id, org_nr, kandidat_id, startdato, sluttdato, ant_timer, faktisk_start, faktisk_slutt, faktisk_ant_timer) VALUES(DEFAULT, 2, 1234, NULL, DATE('2013-11-23'), DATE('2018-11-23'), 12, NULL, NULL, NULL);
INSERT INTO Oppdrag(oppdr_id, kval_id, org_nr, kandidat_id, startdato, sluttdato, ant_timer, faktisk_start, faktisk_slutt, faktisk_ant_timer) VALUES(DEFAULT, 3, 1235, NULL, DATE('1990-10-10'), DATE('2040-10-10'), 1000, NULL, NULL, NULL);

INSERT INTO Vikartjeneste(org_nr, navn, adresse, telefon, epost) VALUES(1233, 'vik', 'asdasdasdasd', 910239, 'asd@asd.co');


--Oppgave d)
--1)
SELECT navn, telefon, epost_adresse FROM Bedrift;
--2)
SELECT oppdr_id, Bedrift.navn, Bedrift.telefon FROM Oppdrag NATURAL JOIN Bedrift;
--3)
SELECT Kandidat.fornavn, Kandidat.etternavn, Kvalifikasjon.beskrivelse, Kandidat.kandidat_id, Kvalifikasjon.kval_id FROM Kandidat NATURAL JOIN Kvalifikasjon NATURAL JOIN Kand_kval;
--4)
SELECT Kandidat.fornavn, Kandidat.etternavn, Kvalifikasjon.beskrivelse, Kandidat.kandidat_id, Kvalifikasjon.kval_id FROM Kandidat LEFT JOIN Kand_kval ON(Kandidat.kandidat_id = Kand_kval.kandidat_id) LEFT JOIN Kvalifikasjon ON(Kvalifikasjon.kval_id = Kand_kval.kval_id);
--5)
SELECT Kandidat.kandidat_id, Kandidat.fornavn, Kandidat.etternavn, Oppdrag.faktisk_slutt, Oppdrag.oppdr_id, Bedrift.navn FROM Kandidat NATURAL JOIN Oppdrag NATURAL JOIN Bedrift;