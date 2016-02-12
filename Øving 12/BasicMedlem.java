import java.time.LocalDate;

/**
 * Created by Knut on 07.02.2016.
 */
public class BasicMedlem extends BonusMedlem {

    public BasicMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, double poeng) {
        super(medlNr, pers, innmeldtDato, poeng);
    }

    @Override
    public String toString() {
        Personalia pers = getPersonalia();
        return pers.getFornavn() + " " + pers.getEtternavn() + ", medlnr: " + getMedlNr() + ", poeng: " + getPoeng() + ", innmldato: " + getInnmeldtDato() + ", type: basicmedlem";
    }
}
