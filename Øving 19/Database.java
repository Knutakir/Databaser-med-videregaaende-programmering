import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

        } catch (SQLException sqlEx) {

        } catch (Exception e) {

        }
    }

    private void utforSporring(String sporring) {

    }

    public boolean regNyBok(Bok nyBok) {

        return false; // returnerer false dersom en bok med samme isbn eksisterer fra før
    }

    public int regNyttEksemplar(String isbn) {

        return 0; // returnerer 0 dersom ingen bøker med samme isbn eksiterer fra før
    }

    public boolean laanUtEksemplar(String isbn, String navn, int eksNr) {

        return false; // returnerer false dersom isbn og/ eller eksempelnummeret ikke eksiterer fra før
    }

    public void lukkForbindelse() {
        try {
            setning.close();
            forbindelse.close();
        } catch (SQLException sqlEx) {

        } catch (Exception e) {

        }
    }
}
