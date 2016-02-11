import java.time.LocalDate;

/**
 * Created by Knut on 07.02.2016.
 */
public class BonusMedlem {
    private final int medlNr;
    private final Personalia pers;
    private final LocalDate innmeldtDato;
    private double poeng = 0;

    public BonusMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato) {
        this.medlNr = medlNr;
        this.pers = pers;
        this.innmeldtDato = innmeldtDato;
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

    public void setPoeng(int poeng){
        this.poeng = poeng;
    }

    public double finnKvalPoeng() {
        return 0;
    }

    public boolean okPassord(String passord) {
        return pers.okPassord(passord);
    }

    public void registrerPoeng(double antPoeng) {
        poeng += antPoeng;
    }
}
