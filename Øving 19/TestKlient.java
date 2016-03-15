import javax.swing.*;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.showOptionDialog;

/**
 * Created by Knut on 09.03.2016.
 */
public class TestKlient {
    public static void main(String[] args) {
        String databaseNavn = "";//"jdbc:mysql://mysql.stud.iie.ntnu.no:3306/knutakir?user=knutakir&password=passord";
        Database databasen = new Database("com.mysql.jdbc.Driver", databaseNavn);

        final int REG_BOK = 0;
        final int REG_EKS = 1;
        final int LAAN_BOK = 2;
        final int AVSLUTT = 3;
        String[] muligheter = {"Registrer ny bok", "Registrer nytt eksempel", "Lån ut eksempel", "Avslutt"};
        int valg = showOptionDialog(null, "Velkommen", "Hva vil du gjøre?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, muligheter, muligheter[0]);

        while(valg != AVSLUTT) {
            switch(valg) {
                case REG_BOK:
                    String isbn = showInputDialog("Skriv inn isbn til boken: ");
                    String forfatter = showInputDialog("Skriv inn navnet til forfatteren: ");
                    String tittel = showInputDialog("Skriv inn tittelen til boken: ");
                    Bok nyBok = new Bok(isbn, forfatter, tittel);
                    if(databasen.regNyBok(nyBok)) {
                        showMessageDialog(null, "Boken ble registrert");
                    } else {
                        showMessageDialog(null, "Kunne ikke registrere en bok med gitte data.");
                    }
                    break;
                
                case REG_EKS:
                    String isbn2 = showInputDialog("Skriv inn isbn til boken: ");
                    int eksempelNr = databasen.regNyttEksemplar(isbn2);
                    if(eksempelNr != 0) {
                        showMessageDialog(null, "Det ble registrert ett nytt eksemplar av denne boken. Eksempelet fikk nummer: " + eksempelNr + ".");
                    } else {
                        showMessageDialog(null, "Det eksisterer ingen bøker med gitt isbn-verdi, derfor kunne ingen nye eksempler registreres.");
                    }
                    break;

                case LAAN_BOK:
                    String isbn3 = showInputDialog("Skriv inn isbn til boken som skal lånes ut: ");
                    String utlaaner = showInputDialog("Skriv inn navnet til personen som skal låne boken: ");
                    int eksempelNr2 = Integer.parseInt(showInputDialog("Skriv inn eksempelnummeret på boken du skal låne ut: "));
                    if(databasen.laanUtEksemplar(isbn3, utlaaner, eksempelNr2)) {
                        showMessageDialog(null, "Eksempelet ble lånet ut.");
                    } else {
                        showMessageDialog(null, "Eksempelet ble ikke lånet ut.");
                    }
                    break;

                default:
                    break;
            }
        }
    }
}