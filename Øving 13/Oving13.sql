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
--SELECT fornavn, etternavn, ansiennitet FROM andelseier ORDER BY ansiennitet desc;
SELECT CONCAT(fornavn, " ", etternavn) AS navn, ansiennitet FROM andelseier ORDER BY ansiennitet DESC;

--3) I hvilket år ble det eldste borettslaget etablert?
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
SELECT COUNT(bygning.bolag_navn) AS antall, borettslag.bolag_navn FROM bygning RIGHT JOIN borettslag ON (bygning.bolag_navn = borettslag.bolag_navn) GROUP BY borettslag.bolag_navn;

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
