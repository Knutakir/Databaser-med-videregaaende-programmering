/**
 * Created by Knut on 16.03.2016.
 */
public class TestKlient {
    public static void main(String[] args) {
        String databaseNavn = "jdbc:mysql://localhost/test";
        String databaseDriver = "com.mysql.jdbc.Driver";
        Database databasen = new Database(databaseNavn, databaseDriver);

    }
}
