import java.time.LocalDate;
import java.time.Period;

/**
 * Created by Knut on 07.02.2016.
 */
public class BonusMedlem {
    private final int medlNr;
    private final Personalia pers;
    private final LocalDate innmeldtDato;
    private double poeng = 0;

    public BonusMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, double poeng) {
        this.medlNr = medlNr;
        this.pers = pers;
        this.innmeldtDato = innmeldtDato;
        this.poeng = poeng;
    }

    public int getMedlNr() {
        return medlNr;
    }

    public Personalia getPersonalia() {
        return pers;
    }

    public LocalDate getInnmeldtDato() {
        return innmeldtDato;
    }

    public double getPoeng() {
        return poeng;
    }

    public void setPoeng(double poeng){
        this.poeng += poeng;
    }

    public double finnKvalPoeng() {
        LocalDate local2 = LocalDate.now();
        int aarMellom = Period.between(getInnmeldtDato(), local2).getYears();
        if(aarMellom > 0) {
            return 0;
        } else {
            return getPoeng();
        }
    }

    public boolean okPassord(String passord) {
        return pers.okPassord(passord);
    }

    public void registrerPoeng(double antPoeng) {
        poeng += antPoeng;
    }
}
