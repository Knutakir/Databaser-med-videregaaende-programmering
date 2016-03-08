package dyrehage;

/**
 * Created by Knut on 04.03.2016.
 */
public class Hunnindivid extends Individ implements SkandinaviskeRovdyr {

    private int antKull;

    public Hunnindivid(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String navn, int fDato, boolean farlig, int antKull) {
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse, navn, fDato, false, farlig);
        this.antKull = antKull;
    }

    public int getAntKull() {
        return antKull;
    }

    @Override
    public void leggTilKull(int antall) {
        antKull += antall;
    }

    @Override
    public void leggTilNyttKull() {
        antKull++;
    }
}
