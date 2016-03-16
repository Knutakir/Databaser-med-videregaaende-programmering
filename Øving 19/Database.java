import java.sql.*;

/**
 * Created by Knut on 09.03.2016.
 */
public class Database {

    private Connection forbindelse;
    private Statement setning;
    private String databaseDriver;
    private String databaseNavn;

    public Database(String databaseDriver, String databaseNavn) {
        this.databaseDriver = databaseDriver;
        this.databaseNavn = databaseNavn;
        startForbindelse();
    }

    private void startForbindelse() {
        try {
            Class.forName(databaseDriver);
            forbindelse = DriverManager.getConnection(databaseNavn);
            setning = forbindelse.createStatement();
        } catch (ClassNotFoundException classEx) {
            System.out.println(classEx.getMessage());
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean regNyBok(Bok nyBok) {
        /**
         * Denne metoden skal registrere en ny tittel og samtidig eksemplar nummer 1 av denne tittelen.

         SQL-setninger:

         insert into boktittel(isbn, forfatter, tittel) values(<isbn>, <forfatter>, <tittel>)
         insert into eksemplar(isbn, eks_nr) values (<isbn>, 1);

         Metoden skal returnere false dersom bok med dette ISBN er registrert fra før. Da skal ingen oppdatering skje.
         */


        if(isbnEksisterer(nyBok.getIsbn())) {
            return false;
        } else {
            try {
                forbindelse.setAutoCommit(false);

                String insert1 = "INSERT INTO boktittel(isbn, forfatter, tittel) VALUES('" + nyBok.getIsbn() + "','" + nyBok.getForfatter() + "','" + nyBok.getTittel() + "');";
                String insert2 = "INSERT INTO eksemplar(isbn, eks_nr) VALUES('" + nyBok.getIsbn() + "', 1);";

                if(setning.executeUpdate(insert1) != 0) {
                    if(setning.executeUpdate(insert2) != 0) {
                        forbindelse.commit();
                        return true;
                    } else {
                        forbindelse.rollback();
                        return false;
                    }
                } else {
                    forbindelse.rollback();
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    forbindelse.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public int regNyttEksemplar(String isbn) {
        /**
         * Denne metoden skal registrere et nytt eksemplar av en tittel som allerede skal være registrert i databasen. Eksemplarnummeret skal være 1 høyere enn hittil største eksemplarnummer. For å være sikret dette, kan ikke sekvenser/autonummerering brukes. (Du har ikke noen garanti for at slike sekvenser er ubrutt). Du må i stedet bruke teknikken som er brukt i metoden registrerNyPerson(), se leksjonen, eventuelt 4.utgave av boka, side 829.

         Metoden skal returnere 0 hvis ingen tittel med dette ISBN eksisterer, ellers skal metoden returnere eksemplarnummeret.

         SQL-setning:

         insert into eksemplar(isbn, eks_nr) values (<isbn>, <eks_nr>);
         */
        if(isbnEksisterer(isbn)) {
            int nyttNr = 1;
            boolean ok = false;
            //int antForsok = 0;
            ResultSet res = null;
            PreparedStatement selectSetning;
            PreparedStatement insertSetning;

            while(!ok) {
                String selectSQL = "SELECT MAX(eks_nr) AS maks FROM eksemplar WHERE isbn = '" + isbn + "';";
                try {
                    selectSetning = forbindelse.prepareStatement(selectSQL);
                    res = selectSetning.executeQuery();

                    res.next();
                    nyttNr = res.getInt("maks") + 1;
                    String insertSQL = "INSERT INTO eksemplar(isbn, eks_nr) VALUES(?, ?);";
                    insertSetning = forbindelse.prepareStatement(insertSQL);
                    insertSetning.setString(1, isbn);
                    insertSetning.setInt(2, nyttNr);
                    insertSetning.executeUpdate();
                    ok = true;
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return nyttNr;
            }
        } else {
            return 0; //returnerer 0 dersom ingen bøker med samme isbn eksiterer fra før
        }
        return 0;
    }

    private boolean isbnEksisterer(String isbn){
        try {
            ResultSet resultSet = setning.executeQuery("SELECT * FROM boktittel WHERE isbn = '" + isbn + "';");
            boolean eksisterer = false;
            while(resultSet.next()) {
                eksisterer = true;
            }
            if(eksisterer) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean laanUtEksemplar(String isbn, String navn, int eksNr) {
        /**
         * Denne metoden skal registrere at ei bok er utlånt. Kolonnen utlånt for det aktuelle eksemplaret skal settes lik navnet til låneren. Du trenger ikke ta hensyn til om dette feltet allerede er utfylt (vi har ingen metode for å returnere ei bok).

         Metoden skal returnere false dersom ISBN og/eller eksemplarnummer er ugyldig. (Tips: Det er nok å sjekke at update-setningen returnerer 0, det vil si at ingen rader er oppdatert).

         update eksemplar set laant_av = <navn> where isbn = <isbn> and eks_nr = <eks_nr>
         */
        String updSetning = "UPDATE eksemplar set laant_av = '" + navn + "' WHERE isbn = '" + isbn + "' AND eks_nr = '" + eksNr + "';";
        try {
            if(setning.executeUpdate(updSetning) != 0){
                //System.out.println("oppdatert");
                return true;
            } else {
                //System.out.println("Ikkje oppdatert");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        //return false; //returnerer false dersom isbn og/ eller eksempelnummeret ikke eksiterer fra før
    }

    public void lukkForbindelse() {
        try {
            setning.close();
            forbindelse.close();
        } catch (SQLException sqlEx) {
            System.out.println("Error");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    //Testing:
    public static void main(String[] args) {
        String databaseNavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/knutakir?user=knutakir&password=passsord";//"jdbc:mysql://localhost/øving 19";//"jdbc:mysql://mysql.stud.iie.ntnu.no:3306/knutakir?user=knutakir&password=passord";
        Database databasen = new Database("com.mysql.jdbc.Driver", databaseNavn);
        //databasen.regNyBok(new Bok("asdas", "asdasd", "asdasd22"));
        //databasen.regNyBok(new Bok("0-07-241163-5", "asdasd", "asdasd22"));
        //databasen.laanUtEksemplar("asdasd", "asdasd", 2);
        //databasen.laanUtEksemplar("0-07-241163-5", "asdasd", 20);
        //databasen.laanUtEksemplar("0-07-241163-5", "Ingunn Sund", 2);
        //System.out.println(databasen.regNyttEksemplar("0-596-00123-1"));
        //  System.out.println(databasen.regNyBok(new Bok("0-596-00123-2", "Test shiiet", "Ingunn Sund")));
        databasen.lukkForbindelse();
    }
}