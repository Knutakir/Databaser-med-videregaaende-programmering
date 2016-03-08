-- Oppgave 2
-- a)
/*
Sett opp en SELECT-setning som er UNION mellom alle forlag med Oslo-nummer (telefonnummer begynner med 2) og alle som ikke er Oslo-nummer.
Får du med forlaget med NULL-verdi på telefonnummer?
Hvis ikke, utvid unionen med en mengde til.
 */
SELECT forlag_navn, telefon FROM forlag WHERE telefon LIKE '2%'
UNION
  SELECT forlag_navn, telefon FROM forlag WHERE telefon NOT LIKE '2%'
UNION
  SELECT forlag_navn, telefon FROM forlag;

-- b)
/*
Sett opp SQL-setninger som finner gjennomsnittlig alder på forfattere der fødselsåret er oppgitt.
For forfattere der dødsåret ikke er oppgitt, skal du kun ta med de som er født etter 1900.
Tips for å få ut året i år:
    MySQL: SELECT YEAR(CURRENT_DATE) FROM ... hvilken tabell som helst ...
 */

 -- Er døde, henter ut navn og alder
SELECT CONCAT(fornavn, " ", etternavn) AS navn, CONCAT(dod_aar - fode_aar) AS alder FROM forfatter WHERE dod_aar IS NOT NULL;

-- lever enda, og er født etter 1900
SELECT CONCAT(fornavn, " ", etternavn) AS navn, CONCAT(YEAR(CURRENT_DATE) - fode_aar) AS alder FROM forfatter WHERE fode_aar >= 1900 AND dod_aar IS NULL;

--Lage et view av begge select-setningene
CREATE VIEW forfatterview AS
  SELECT CONCAT(fornavn, " ", etternavn) AS navn, CONCAT(dod_aar - fode_aar) AS alder FROM forfatter WHERE dod_aar IS NOT NULL
UNION
  SELECT CONCAT(fornavn, " ", etternavn) AS navn, CONCAT(YEAR(CURRENT_DATE) - fode_aar) AS alder FROM forfatter WHERE fode_aar >= 1900 AND dod_aar IS NULL;

--Select-setning som gir ut gjennomsnittlig alder til forfattere:
SELECT CONCAT(SUM(alder) / COUNT(*)) gj_snitt_alder FROM forfatterview;

-- c)
/*
Sett opp SQL-setninger som finner hvor stor andel av forfatterne som ble med i beregningene under b).
 */
SELECT CONCAT(COUNT(*) / (SELECT COUNT(*) FROM forfatter)* 100) AS prosentdel_forfattere FROM forfatterview;