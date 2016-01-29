----------------------------------

-- Oppgave 1

-- Opppagve a)
-- Seleksjon
SELECT * FROM `bok` WHERE `utgitt_aar` = 1996;
-- Projeksjon
SELECT DISTINCT tittel, utgitt_aar FROM bok;

-- Oppgave b) - produkt
SELECT * FROM forlag, bok;
-- resultatet blir en kombinasjon mellom forlag og bok. Det som kommer som resultat er allemulige kombinasjoner mellom bok og forlag.

-- Oppgave c) - på tabellene forlag og bok
--likhetsforening (equijoin)
SELECT * FROM forlag, bok WHERE bok.forlag_id = forlag.forlag_id;
-- naturlig forening (natural join)
SELECT * FROM forlag NATURAL JOIN bok; -- Attributtene har samme verdi, og vi fjerner dublikasjonsattributtet.
-- De tuplene som har sammenfallende verdier i de spesifiserte attributtene blir skrevet ut. Vi setter de to felles attributtene i hver relasjon lik hverandre.

-- Oppgave d) - Unionkompabilitet
SELECT bok_id FROM bok UNION SELECT bok_id FROM bok_forfatter;
SELECT fornavn FROM konsulent UNION SELECT fornavn FROM forfatter;

----------------------------------


-- Oppgave 2
-- Oppagve a) - Projeksjon
SELECT forlag_navn FROM forlag;
-- Projeksjon

-- Oppgave b) - Seleksjon
SELECT forlag_id FROM forlag WHERE forlag_id NOT IN (SELECT forlag_id FROM bok);

-- Oppgave c) - Seleksjon
SELECT * FROM forfatter WHERE fode_aar = 1948;

-- Oppgave d) - naturlig forening (natural join) og seleksjon
SELECT forlag_navn, adresse FROM forlag NATURAL JOIN bok WHERE tittel = 'Generation x';

-- Oppgave e) - naturlig forening (natural join) og seleksjon
SELECT tittel FROM bok NATURAL JOIN forfatter NATURAL JOIN bok_forfatter WHERE forfatter.etternavn = 'Hamsun';

-- Oppgave f) - naturlig forening (natural join) og seleksjon
SELECT bok.tittel, bok.utgitt_aar, forlag.forlag_navn, forlag.adresse, forlag.telefon FROM bok RIGHT JOIN forlag ON(bok.forlag_id = forlag.forlag_id);