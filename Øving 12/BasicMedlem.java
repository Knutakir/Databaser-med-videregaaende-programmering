import java.time.LocalDate;
import java.time.Period;

/**
 * Created by Knut on 07.02.2016.
 */
public class BasicMedlem extends BonusMedlem {

    public BasicMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato) {
        super(medlNr, pers, innmeldtDato);
    }

    @Override
    public double finnKvalPoeng() {
        LocalDate local2 = LocalDate.now();
        if(Period.between(local2, getInnmeldtDato()).getYears() < 1) {
            return 25000 - getPoeng();
        } else {
            return 0;
        }
    }
}
