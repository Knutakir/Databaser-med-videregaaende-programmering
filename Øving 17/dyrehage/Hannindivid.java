package dyrehage;

/**
 * Created by Knut on 04.03.2016.
 */
public class Hannindivid extends Individ implements SkandinaviskeRovdyr {

    public Hannindivid(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String navn, int fDato, boolean farlig) {
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse, navn, fDato, true, farlig);
    }

    public int getAntKull() {
        return 0;
    }

    @Override
    public void leggTilKull(int antall) {
    }

    @Override
    public void leggTilNyttKull() {
    }
}
