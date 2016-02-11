import java.time.LocalDate;
import java.time.Period;

/**
 * Created by Knut on 07.02.2016.
 */
public class SoelvMedlem extends BonusMedlem {

    private double poeng = 0;

    public SoelvMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, double poeng) {
        super(medlNr, pers, innmeldtDato);
        this.poeng = poeng;
    }

    @Override
    public double finnKvalPoeng() {
        LocalDate local2 = LocalDate.now();
        if(Period.between(local2, getInnmeldtDato()).getYears() < 1) {
            return 75000 - getPoeng();
        } else {
            return 0;
        }
    }
    @Override
    public void registrerPoeng(double antPoeng) {
        poeng += (antPoeng * 1.2);
    }
}
