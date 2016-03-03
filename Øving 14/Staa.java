/**
 * Created by Knut on 16.02.2016.
 */
public class Staa extends Tribune {

    private int antSolgteBilletter = 0;

    public Staa(String tribunenavn, int kapasitet, double pris){
        super(tribunenavn, kapasitet, pris);
    }

    @Override
    public int finnAntallSolgteBilletter() {
        return antSolgteBilletter;
    }

    @Override
    public double finnInntekt() {
        return (antSolgteBilletter * getPris());
    }

    @Override
    public Billett[] kjopBilletter(int antallBilletter) {
        Billett[] billettListe = new Billett[antallBilletter];
        if((getKapasitet() - antSolgteBilletter) >= antallBilletter) {
            for(int i = 0; i < antallBilletter; i++) {
                StaaplassBillett staaBillett = new StaaplassBillett(getTribunenavn(), getPris());
                billettListe[i] = staaBillett;
                antSolgteBilletter++;
            }
        } else {
            return null;
        }
        return billettListe;
    }

    @Override
    public Billett[] kjopBilletter(String[] navneliste) {
        return kjopBilletter(navneliste.length);
    }
}
