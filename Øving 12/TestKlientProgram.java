import java.time.LocalDate;

import static javax.swing.JOptionPane.*;

/**
 * Created by Bruker on 10.02.2016.
 */
public class TestKlientProgram {
    public static void main(String[] args){
        Medlemsarkiv med = new Medlemsarkiv();
        //Registrere medlemmer:
        Personalia pers1 = new Personalia("Knut", "Kirkhorn", "asd@asd.no", "blablabla1234");
        med.nyMedlem(pers1, LocalDate.of(2006, 2, 15));
        Personalia pers2 = new Personalia("Ingunn", "Sund", "asd2@asd.no", "sadasd2221r");
        med.nyMedlem(pers2, LocalDate.of(2015, 2, 15));

        final int FINN_POENG = 0;
        final int REG_POENG = 1;
        final int REG_MEDL = 2;
        final int SJEKK_MEDL = 3;
        final int ALLE_MEDL = 4;
        final int AVSLUTT = 5;

        String[] muligheter = {"Finn poeng til medlem", "Registrer poeng", "Registrere et nytt medlem", "Sjekk medlemmer for oppgradering", "Alle medlemmer", "Avslutt"};

        int valg = showOptionDialog(null, "Hva vil du gjøre?", "Øving 12 - testklient", YES_NO_OPTION, INFORMATION_MESSAGE, null, muligheter, muligheter[0]);

        while(valg != AVSLUTT){
            switch (valg){
                case FINN_POENG:
                    int medlemsNummer = Integer.parseInt(showInputDialog("Skriv inn medlemsnummer:"));
                    String passord = showInputDialog("Skriv inn passord:");
                    double poeng = med.finnPoeng(medlemsNummer, passord);
                    double grense = -1.0;
                    if(poeng > grense){
                        showMessageDialog(null, "Antall poeng til bruker med medlemsnummer: " + medlemsNummer + ": " + poeng);
                    } else {
                        showMessageDialog(null, "Fant ikke noen bruker med dataen du skrev inn.");
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

                    med.nyMedlem(pers, LocalDate.now());
                    showMessageDialog(null, "Det ble registrert et nytt medlem.");
                    break;

                case SJEKK_MEDL:
                    if(med.sjekkMedlemmer()){
                        showMessageDialog(null, "Du har oppgradert medlemmer.");
                    } else {
                        showMessageDialog(null, "Det var ingen medlemmer å oppgradere. Alle var fullt oppgradert.");
                    }
                    break;

                case ALLE_MEDL:
                    showMessageDialog(null, med.toString());
                    break;
            }
            valg = showOptionDialog(null, "Hva vil du gjøre?", "Øving 12 - testklient", YES_NO_OPTION, INFORMATION_MESSAGE, null, muligheter, muligheter[0]);

        }
    }
}
