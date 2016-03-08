package dyrehage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

/**
 * Created by Knut on 04.03.2016.
 */
public abstract class Individ extends Dyr implements SkandinaviskeRovdyr {
    private final String navn;
    private final int fDato;
    private final boolean hanndyr;
    private final boolean farlig;

    public Individ(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String navn, int fDato, boolean hanndyr, boolean farlig) {
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse);
        this.navn = navn;
        this.fDato = fDato;
        this.hanndyr = hanndyr;
        this.farlig = farlig;
    }

    public String getNavn() {
        return navn;
    }

    public int getFDato() {
        return fDato;
    }

    @Override
    public int getAlder() {
        /*LocalDate no = LocalDate.now();
        LocalDate foer = LocalDate.parse(Integer.toString(getFDato()));

        return Period.between(foer, no).getYears();*/

        int fodselsAar = Integer.parseInt(Integer.toString(getFDato()).substring(0, 4));
        return LocalDate.now().getYear() - fodselsAar;
    }

    @Override
    public void flytt(String nyAdresse) {
        setAdresse(nyAdresse);
    }

    @Override
    public String skrivUtInfo() {
        return toString();
    }

    public boolean getHanndyr() {
        return hanndyr;
    }

    public boolean getFarlig() {
        return farlig;
    }

    @Override
    public String toString() {
        String kjonn = "";
        if(getHanndyr()) {
            kjonn = "Hann";
        } else {
            kjonn = "Hunn";
        }
        String utskrift = super.toString() + "\nNavn: " + navn + "\nFødselsdato: " + fDato + "\nKjønn: " + kjonn + "\nEr farlig: " + getFarlig();
        return utskrift;
    }
}
