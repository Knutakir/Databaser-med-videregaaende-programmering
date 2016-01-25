import static javax.swing.JOptionPane.*;
import java.sql.*;

class OppgaveB {
	public static void main(String[] args) {
		try{
			String databasedriver = "com.mysql.jdbc.Driver";
			Class.forName(databasedriver);

			String databaseNavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/knutakir?user=knutakir&password=passord";
			Connection forbindelse = DriverManager.getConnection(databaseNavn);

			Statement setning = forbindelse.createStatement();

			//0-02-219233-1

			String person = showInputDialog("Skriv inn navnet: ");
			String isbn = showInputDialog("Skriv inn isbn: ");
			int eksemplarNummer = Integer.parseInt(showInputDialog("Skriv inn eksemplarnummer: "));

			String sqlSetning = "UPDATE eksemplar SET laant_av = '" + person + "' WHERE isbn = '" + isbn + "' AND eks_nr = " + eksemplarNummer + " AND laant_av is NULL;";

			if(setning.executeUpdate(sqlSetning) == 0){
				showMessageDialog(null, "Databasen ble ikke oppdatert");
			} else {
				showMessageDialog(null, "Databasen ble oppdatert");
			}

			//UPDATE eksemplar SET laant_av = 'Per Olsen' WHERE isbn = '0-07-241163-5' AND eks_nr = 1 AND laant_av is NULL;
			setning.close();
			forbindelse.close();
		} catch (Exception e){
			showMessageDialog(null, e.getMessage());
		}

	}
}