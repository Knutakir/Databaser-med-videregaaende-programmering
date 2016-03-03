/**
 * Created by Knut on 16.02.2016.
 */
public abstract class Tribune {//implements Comparable{

    private final String tribunenavn;
    private final int kapasitet;
    private final double pris;

    public Tribune(String tribunenavn, int kapasitet, double pris) {
        this.tribunenavn = tribunenavn;
        this.kapasitet = kapasitet;
        this.pris = pris;
    }

    public String getTribunenavn() {
        return tribunenavn;
    }

    public int getKapasitet() {
        return kapasitet;
    }

    public double getPris() {
        return pris;
    }

    abstract public int finnAntallSolgteBilletter();

    public double finnInntekt() {
        return (finnAntallSolgteBilletter() * getPris());
    }

    abstract public Billett[] kjopBilletter(int antallBilletter);

    abstract public Billett[] kjopBilletter(String[] navneliste);

    public String toString() {
        return "Navn: " + getTribunenavn() + "\nKapasitet: " + getKapasitet() + "\nAntall solgte billetter: " + finnAntallSolgteBilletter() + "\nInntekt: " + finnInntekt();
    }
}
