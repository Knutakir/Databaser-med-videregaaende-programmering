import java.time.LocalDate;

/**
 * Created by Knut on 07.02.2016.
 */
public class GullMedlem extends BonusMedlem {

    double poeng = 0;

    public GullMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, double poeng) {
        super(medlNr, pers, innmeldtDato);
        this.poeng = poeng;
    }

    @Override
    public void registrerPoeng(double antPoeng){
        poeng += (antPoeng * 1.5);
    }
}
