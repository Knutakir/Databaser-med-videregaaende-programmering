import javax.swing.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static javax.swing.JOptionPane.*;
//import static javax.swing.JOptionPane.showInputDialog;
//import static javax.swing.JOptionPane.showMessageDialog;
//import static javax.swing.JOptionPane.showOptionDialog;

/**
 * Created by Bruker on 10.02.2016.
 */
public class TestKlientProgram {
    public static void main(String[] args){
        System.out.println("hei");
        showMessageDialog(null, "test");
        Medlemsarkiv med = new Medlemsarkiv();
        //Registrere medlemmer:
        Personalia pers1 = new Personalia("Knut", "Kirkhorn", "asd@asd.no", "blablabla1234");
        med.nyMedlem(pers1, LocalDate.of(2006, 2, 15));
        Personalia pers2 = new Personalia("asd", "asd", "asd2@asd.no", "sadasd2221r");
        med.nyMedlem(pers2, LocalDate.of(2007, 2, 15));

        showMessageDialog(null, "test");

        final int FINN_POENG = 0;
        final int REG_POENG = 1;
        final int REG_MEDL = 2;
        final int SJEKK_MEDL = 3;
        final int AVSLUTT = 4;

        String[] muligheter = {"Finn poeng til medlem", "Registrer poeng", "Registrere et nytt medlem", "Sjekk medlemmer for oppgradering", "Avslutt"};

        int valg = showOptionDialog(null, "Hva vil du gjøre?", "Øving 12 - testklient", YES_NO_OPTION, INFORMATION_MESSAGE, null, muligheter, muligheter[0]);

        while(valg != AVSLUTT){
            switch (valg){
                case FINN_POENG:
                    int medlemsNummer = Integer.parseInt(showInputDialog("Skriv inn medlemsnummer:"));
                    String passord = showInputDialog("Skriv inn passord:");
                    double poeng = med.finnPoeng(medlemsNummer, passord);
                    if(poeng > 0){
                        showMessageDialog(null, "Antall poeng til bruker med medlemsnummer: " + medlemsNummer + ": " + poeng);
                    } else {
                        showMessageDialog(null, "Fant ikke noen bruker med medlemsnummer du skrev inn(" + medlemsNummer + ").");
                    }
                    break;
                case REG_POENG:
                    int regMedlemsNummer = Integer.parseInt(showInputDialog("Skriv inn medlemsnummer:"));
                    int antallPoeng = Integer.parseInt(showInputDialog("Skriv inn antall poeng:"));
                    if(med.registrerPoeng(regMedlemsNummer, antallPoeng)){
                        showMessageDialog(null, "Det ble registrert " + antallPoeng + " til brukeren med medlemsnummer " + regMedlemsNummer + ".");
                    } else {
                        showMessageDialog(null, "Det ble ikke registrert noe poeng, siden brukeren ikke ble funnet.");
                    }
                    break;

                case REG_MEDL:
                    String fornavn = showInputDialog("Skriv inn et fornavn:");
                    String etternavn = showInputDialog("Skriv inn et etternavn:");
                    String epostaddr = showInputDialog("Skriv inn en epostadresse:");
                    String medlPassord = showInputDialog("Skriv inn et passord:");
                    Personalia pers = new Personalia(fornavn, etternavn, epostaddr, medlPassord);

                    /*int aar = Integer.parseInt(showInputDialog("Skriv inn et år:"));
                    int maaned = Integer.parseInt(showInputDialog("Skriv inn en måned:"));
                    int dag = Integer.parseInt(showInputDialog("Skriv inn en dag:"));*/
                    String dato = showInputDialog("Skriv inn en dato (yyyy-MM-dd"); //2005-nov-12
                    DateTimeFormatter formatterer = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
                    LocalDate dato2 = LocalDate.parse(dato, formatterer);
                    med.nyMedlem(pers, dato2);
                    showMessageDialog(null, "Det ble registrert et nytt medlem.");
                    break;

                case SJEKK_MEDL:
                    if(med.sjekkMedlemmer()){
                        showMessageDialog(null, "Du har oppgradert de medlemmer.");
                    } else {
                        showMessageDialog(null, "Det var ingen medlemmer å oppgradere. Alle var fullt oppgradert.");
                    }
                    break;
            }
            valg = showOptionDialog(null, "Hva vil du gjøre?", "Øving 12 - testklient", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, muligheter, muligheter[0]);

        }
    }
}
