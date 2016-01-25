import static javax.swing.JOptionPane.*;
import java.sql.*;

class OppgaveA{
	public static void main(String[] args) {
		try {
			String databasedriver = "com.mysql.jdbc.Driver";
			Class.forName(databasedriver);

			String databaseNavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/knutakir?user=knutakir&password=passord";
			Connection forbindelse = DriverManager.getConnection(databaseNavn);

			Statement setning = forbindelse.createStatement();

			String bokISBN = showInputDialog("Skriv inn isbn: "); //0-02-219233-1

			ResultSet res = setning.executeQuery("SELECT count(*) eks_nr FROM eksemplar WHERE isbn = '" + bokISBN + "';");

			String utskrift = "";
			boolean eksistererEks = false;
			while(res.next()){
				int antEksemplar = res.getInt("eks_nr");
				if(antEksemplar == 0){
					showMessageDialog(null, "Det eksisterer ingen ekemplarer av denne boken.");
				} else {
					utskrift += "\nAntall eksamplar: " + antEksemplar;
					eksistererEks = true;
				}
			}

			if(eksistererEks){
				res = setning.executeQuery("SELECT forfatter, tittel FROM boktittel WHERE isbn = '" + bokISBN + "';");//'0-02-219233-1';");

				while(res.next()){
					String forfatter = res.getString("forfatter");
					String bokTittel = res.getString("tittel");
					utskrift = "Forfatter: " + forfatter + "\nBoktittel: " + bokTittel + utskrift;
				}

				showMessageDialog(null, utskrift);
			}

			res.close();
			setning.close();
			forbindelse.close();
		} catch (Exception e){
			showMessageDialog(null, e.getMessage());
		}
	}
}