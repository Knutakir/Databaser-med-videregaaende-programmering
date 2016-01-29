-- borettslag --
INSERT INTO borettslag(bor_id, navn, adresse, etableringsAar) VALUES(DEFAULT, 'testing', 'Test gata 2', 1996);
INSERT INTO borettslag(bor_id, navn, adresse, etableringsAar) VALUES(DEFAULT, 'test2', 'Test gata 3', 1996);

-- bygning --
INSERT INTO bygning(bygn_id, etasjer, leiligheter, bor_id) VALUES(DEFAULT, 3, 6, 1);
INSERT INTO bygning(bygn_id, etasjer, leiligheter, bor_id) VALUES(DEFAULT, 2, 7, 2);

-- leilighet --
INSERT INTO leilighet(leilighets_id, antRom, antM2, etasje, leilighetsNr, bygn_id) VALUES(DEFAULT, 3, 30, 2, 2, 1);
INSERT INTO leilighet(leilighets_id, antRom, antM2, etasje, leilighetsNr, bygn_id) VALUES(DEFAULT, 2, 23, 1, 1, 2);

-- andelseier --
INSERT INTO andelseier(andeier_id, navn, leilighets_id) VALUES(DEFAULT, 'Ola Nordmann', 1);
INSERT INTO andelseier(andeier_id, navn, leilighets_id) VALUES(DEFAULT, 'Kari Nordmann', 2);



-- Setninger med brudd på entitets- og referanseintegritetsreglene --
-- #1 --
INSERT INTO bygning(bygn_id, etasjer, leiligheter, bor_id) VALUES(DEFAULT, 3, 6, 7);
-- Denne gir feilmelding:
-- #1452 - Cannot add or update a child row: a foreign key constraint fails (`knutakir`.`bygning`, CONSTRAINT `bygning_ibfk_1` FOREIGN KEY (`bor_id`) REFERENCES `borettslag` (`bor_id`)) --

-- #2 --
INSERT INTO leilighet(leilighets_id, antRom, antM2, etasje, leilighetsNr, bygn_id) VALUES(DEFAULT, 3, 30, 2, 2, 10);
-- denne gir feilmelding:
-- #1452 - Cannot add or update a child row: a foreign key constraint fails (`knutakir`.`leilighet`, CONSTRAINT `leilighet_ibfk_1` FOREIGN KEY (`bygn_id`) REFERENCES `bygning` (`bygn_id`)) --

-- #3 --
INSERT INTO andelseier(andeier_id, navn, leilighets_id) VALUES(DEFAULT, 'Knut Kirkhorn', 3);
-- denne gir feilmelding:
--#1452 - Cannot add or update a child row: a foreign key constraint fails (`knutakir`.`andelseier`, CONSTRAINT `andelseier_ibfk_1` FOREIGN KEY (`leilighets_id`) REFERENCES `leilighet` (`leilighets_id`)) --