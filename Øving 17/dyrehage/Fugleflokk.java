package dyrehage;

/**
 * Created by Knut on 04.03.2016.
 */
public class Fugleflokk extends Dyregruppe {

    private final double gjennomsnittligVekt;
    private final boolean svommer;

    public Fugleflokk(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String gruppenavn, int antIndivider, double gjennomsnittligVekt, boolean svommer) {
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse, gruppenavn, antIndivider);
        this.gjennomsnittligVekt = gjennomsnittligVekt;
        this.svommer = svommer;
    }

    public double getGjennomsnittligVekt() {
        return gjennomsnittligVekt;
    }

    public boolean getSvommer() {
        return svommer;
    }

    @Override
    public String toString() {
        return super.toString() + "\nGjennomsnittlig vekt: " + gjennomsnittligVekt + "\nEr en svømmer: " + svommer;
    }
}
