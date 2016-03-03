/**
 * Created by Knut on 16.02.2016.
 */
public class VIP extends Sitte {

    private String[][] tilskuer; //størrelse = antall rader * antall plasser pr rad

    public VIP(String tribunenavn, int kapasitet, double pris, int antallRader) {
        super(tribunenavn, kapasitet, pris, antallRader);
        tilskuer = new String[antallRader][getKapasitet() / antallRader];
    }

    @Override
    public Billett[] kjopBilletter(int antallBilletter) {
        return null;
    }

    @Override
    public Billett[] kjopBilletter(String[] navneliste) {
        Billett[] billettListe = new Billett[navneliste.length];
        if((getKapasitet() - finnAntallSolgteBilletter()) >= navneliste.length) {
            for(int i = 0; i < navneliste.length; i++) {
                int[] ledigRad = finnLedigPlass(navneliste.length);
                SitteplassBillett billetten = new SitteplassBillett(getTribunenavn(), getPris(), ledigRad[0], ledigRad[1]);
                billettListe[i] = billetten;
                tilskuer[ledigRad[0]][ledigRad[1]] = navneliste[i];
            }
            return billettListe;
        }
        return null;
    }

    private int[] finnLedigPlass(int antallPlasser) {
        int[] radPlass = new int[2];
        int startPlass = -1;
        for(int i = 0; i < tilskuer.length; i++) {
            int teller = tilskuer[i].length;//antallPlasser;
            for(int j = 0; j < antallPlasser; j++) {//tilskuer[i].length; j++) {
                if(tilskuer[i][j] == null) {
                    teller--;
                    if(startPlass == -1) {
                        startPlass = j;
                        radPlass[1] = startPlass;
                    }
                }
            }
            if(teller >= 0) {
                radPlass[0] = i;
                return radPlass;
            }
        }
        return null;
    }

    @Override
    public int finnAntallSolgteBilletter() {
        int solgte = 0;
        for(int i = 0; i < tilskuer.length; i++) {
            for(int j = 0; j < tilskuer[i].length; j++) {
                if(tilskuer[i][j] != null) {
                    solgte++;
                }
            }
        }
        return solgte;
    }
}