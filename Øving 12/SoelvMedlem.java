import java.time.LocalDate;

/**
 * Created by Knut on 07.02.2016.
 */
public class SoelvMedlem extends BonusMedlem {

    public SoelvMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, double poeng) {
        super(medlNr, pers, innmeldtDato, poeng);
    }

    @Override
    public void registrerPoeng(double antPoeng) {
        setPoeng(antPoeng * 1.2);
    }

    @Override
    public String toString() {
        Personalia pers = getPersonalia();
        return pers.getFornavn() + " " + pers.getEtternavn() + ", medlnr: " + getMedlNr() + ", poeng: " + getPoeng() + ", innmldato: " + getInnmeldtDato() + ", type: sølvmedlem";
    }
}
