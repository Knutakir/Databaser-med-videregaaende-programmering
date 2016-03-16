import javax.swing.*;

import static javax.swing.JOptionPane.*;

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

        //Variabler som brukes flere plasser:
        String isbn;

        while(valg != AVSLUTT) {
            switch(valg) {
                case REG_BOK:
                    isbn = showInputDialog("Skriv inn isbn til boken: ");
                    String forfatter = showInputDialog("Skriv inn navnet til forfatteren: ");
                    String tittel = showInputDialog("Skriv inn tittelen til boken: ");
                    Bok nyBok = new Bok(isbn, tittel, forfatter);
                    if(databasen.regNyBok(nyBok)) {
                        showMessageDialog(null, "Boken ble registrert");
                    } else {
                        showMessageDialog(null, "Kunne ikke registrere en bok med gitte data.");
                    }
                    break;

                case REG_EKS:
                    isbn = showInputDialog("Skriv inn isbn til boken: ");
                    int eksemplarNr = databasen.regNyttEksemplar(isbn);
                    if(eksemplarNr != 0) {
                        showMessageDialog(null, "Det ble registrert ett nytt eksemplar av denne boken. Eksempelet fikk nummer: " + eksemplarNr + ".");
                    } else {
                        showMessageDialog(null, "Det eksisterer ingen bøker med gitt isbn-verdi, derfor kunne ingen nye eksempler registreres.");
                    }
                    break;

                case LAAN_BOK:
                    isbn = showInputDialog("Skriv inn isbn til boken: ");
                    String utlaaner = showInputDialog("Skriv inn navn til personen som skal leie eksemplaret: ");
                    int eksNr = Integer.parseInt(showInputDialog("Skriv inn eksemplarnummer som skal lånes ut: "));
                    if(databasen.laanUtEksemplar(isbn, utlaaner, eksNr)) {
                        showMessageDialog(null, "Det ble lånt ut et eksemplar til " + utlaaner + ".");
                    } else {
                        showMessageDialog(null, "Det ble ikke lånt ut noe eksemplar");
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
