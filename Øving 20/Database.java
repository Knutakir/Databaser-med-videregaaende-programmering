import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Knut on 16.03.2016.
 */
public class Database {

    private Connection forbindelse;
    private Statement setning;
    private String databaseDriver;
    private String databaseNavn;

    public Database(String databaseNavn, String databaseDriver) {
        this.databaseDriver = databaseDriver;
        this.databaseNavn = databaseNavn;
        startForbindelse();
    }

    private void startForbindelse() {
        try{
            Class.forName(databaseDriver);
            forbindelse = DriverManager.getConnection(databaseNavn);
            setning = forbindelse.createStatement();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fakturer(int maanedsnummer, String tekstfil) {

    }

    public void stoppForbindelse() {
        try{
            setning.close();
            forbindelse.close();
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
