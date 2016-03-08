-- Oppgave 1 --

-- a)
/*
List ut all informasjon (ordrehode og ordredetalj) om ordrer for leverandør nr 44.
 */
SELECT * FROM ordrehode NATURAL JOIN ordredetalj WHERE levnr = 44;

-- b)
/*
Finn navn og by ("LevBy") for leverandører som kan levere del nummer 1.
 */
SELECT navn, levby FROM levinfo NATURAL JOIN prisinfo WHERE delnr = 1;

-- c)
/*
Finn nummer, navn og pris for den leverandør som kan levere del nummer 201 til billigst pris.
 */
SELECT levnr, navn, prisinfo.pris FROM levinfo NATURAL JOIN prisinfo WHERE prisinfo.delnr = 201 FETCH FIRST ROWS ONLY;

SELECT levnr, navn, prisinfo.pris FROM levinfo NATURAL JOIN prisinfo WHERE prisinfo.pris = (SELECT MIN(pris) FROM prisinfo WHERE prisinfo.delnr = 201);


-- d)
/*
Lag fullstendig oversikt over ordre nr 16, med ordrenr, dato, delnr, beskrivelse, kvantum, (enhets-)pris og beregnet beløp (=pris*kvantum).
 */
SELECT ordredetalj.ordrenr, ordrehode.dato, delnr, delinfo.beskrivelse, ordredetalj.kvantum, prisinfo.pris, CONCAT(prisinfo.pris * ordredetalj.kvantum) AS beregnet_beløp FROM ordredetalj
    NATURAL JOIN ordrehode
    NATURAL JOIN delinfo
    NATURAL JOIN prisinfo
  WHERE ordrehode.ordrenr = 16;

-- e)
/*
Finn delnummer og leverandørnummer for deler som har en pris som er høyere enn prisen for del med katalognr X7770.
 */
SELECT delnr, levnr, pris FROM prisinfo WHERE pris > (SELECT pris FROM prisinfo WHERE katalognr = 'X7770');

-- f)
/*
i) Tenk deg at tabellen levinfo skal deles i to. Sammenhengen mellom by og fylke skal tas ut av tabellen.
Det er unødvendig å lagre fylketilhørigheten for hver forekomst av by.
Lag én ny tabell som inneholder byer og fylker. Fyll denne med data fra levinfo.
lag også en tabell som er lik levinfo unntatt kolonnen Fylke. (Denne splittingen av tabellen levinfo gjelder bare i denne oppgaven. I resten av oppgavesettet antar du at du har den opprinnelige levinfo-tabellen.)
 */
CREATE TABLE levinfo2(
  levnr INTEGER,
  levby VARCHAR(20) NOT NULL,
  fylke VARCHAR(20) NOT NULL,
  CONSTRAINT levinfo2_pk PRIMARY KEY(levnr));
);

CREATE TABLE levinfo3(
  levnr INTEGER,
  navn VARCHAR(20) NOT NULL,
  adresse VARCHAR(20) NOT NULL,
  levby VARCHAR(20) NOT NULL,
  postnr INTEGER NOT NULL,
  CONSTRAINT levinfo3_pk PRIMARY KEY(levnr));
);

/*
ii) Lag en virtuell tabell (view) slik at brukerne i størst mulig grad kan jobbe på samme måte mot de to nye tabellene som den gamle.
Prøv ulike kommandoer mot tabellen (select, update, delete, insert).
Hvilke begrensninger, hvis noen, har brukerne i forhold til tidligere?
 */
CREATE VIEW levinfoview AS
  SELECT levnr, navn, adresse, levbynr, levinfo2.fylke, postnr FROM levinfo3 JOIN levinfo2 ON(levinfo2.levnr = levinfo3.levnr);

-- g)
/*
Anta at en vurderer å slette opplysningene om de leverandørene som ikke er representert i Prisinfo-tabellen.
Finn ut hvilke byer en i tilfelle ikke får leverandør i. (Du skal ikke utføre slettingen.) (Tips: Svaret skal bli kun én by, "Ål".)
 */
SELECT levby FROM levinfo WHERE levby NOT IN(SELECT levby FROM levinfo NATURAL JOIN prisinfo);

-- h)
-- et view som kan levere noen av delene eller alle:
CREATE VIEW leverere AS
  SELECT levnr, prisinfo.delnr, CONCAT(pris * ordredetalj.kvantum) AS totalpris FROM prisinfo
  JOIN ordredetalj ON prisinfo.delnr = ordredetalj.delnr
  WHERE ordredetalj.ordrenr = 18;

--et view som kan levere alle delene:
CREATE VIEW kan_levere AS
  SELECT levnr, SUM(totalpris) AS totalpris FROM leverere
  GROUP BY levnr HAVING COUNT(*) = (SELECT COUNT(*) FROM ordredetalj WHERE ordrenr = 18);

--finne hvem som leverer billigst utifra viewet "kan_levere":
SELECT levnr FROM kan_levere WHERE kan_levere.totalpris = (SELECT MIN(totalpris) AS totalpris FROM kan_levere);
