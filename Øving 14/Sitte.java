/**
 * Created by Knut on 16.02.2016.
 */
public class Sitte extends Tribune {

    private int[] antOpptatt; //størrelse = antall seterader

    public Sitte(String tribunenavn, int kapasitet, double pris, int antallRader) {
        super(tribunenavn, kapasitet, pris);
        antOpptatt = new int[antallRader];
    }

    @Override
    public int finnAntallSolgteBilletter() {
        int antallSolgte = 0;
        for(int i = 0; i < antOpptatt.length; i++) {
            antallSolgte += antOpptatt[i];
        }
        return antallSolgte;
    }

    private int finnLedigPlass(int antallBilletter) {
        int kapPrRad = getKapasitet() / antOpptatt.length;

        if(antallBilletter > getKapasitet()) {
            return -1;
        }

        for(int i = 0; i < antOpptatt.length; i++) {
            if((antOpptatt[i] < kapPrRad) && (kapPrRad - antOpptatt[i]) >= antallBilletter) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Billett[] kjopBilletter(int antallBilletter) {
        Billett[] billettListe = new Billett[antallBilletter];
        int ledig = finnLedigPlass(antallBilletter);
        if((ledig != -1) && ((getKapasitet() - finnAntallSolgteBilletter()) >= antallBilletter)) {
            for(int i = 0; i < antallBilletter; i++) {
                int ledigRad = finnLedigPlass(antallBilletter);
                antOpptatt[ledigRad]++;
                SitteplassBillett billetten = new SitteplassBillett(getTribunenavn(), getPris(), antOpptatt[ledigRad], ledigRad);
                billettListe[i] = billetten;
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
