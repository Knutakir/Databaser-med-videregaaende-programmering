package dyrehage;

/**
 * Created by Knut on 04.03.2016.
 */
public abstract class Dyregruppe extends Dyr {

    private final String gruppenavn;
    private int antIndivider;

    public Dyregruppe(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String gruppenavn, int antIndivider) {
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse);
        this.gruppenavn = gruppenavn;
        this.antIndivider = antIndivider;
    }

    public String getGruppenavn() {
        return gruppenavn;
    }

    public int getAntIndivider() {
        return antIndivider;
    }

    public void setAntIndivider(int nyttAntall) {
        antIndivider = nyttAntall;
    }

    @Override
    public String getNorskNavn() {
        return "Gruppe av: " + super.getNorskNavn();
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + getNorskNavn() + "\nGruppenavn: " + gruppenavn + "\nAntall individer: " + antIndivider;
    }
}
