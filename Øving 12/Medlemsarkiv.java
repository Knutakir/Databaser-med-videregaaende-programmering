import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Knut on 07.02.2016.
 */
public class Medlemsarkiv {

    private ArrayList<BonusMedlem> medlemsListe = new ArrayList();

    public Medlemsarkiv() {
        System.out.println("hei");
    }

    public double finnPoeng(int medlNr, String passord) {
        for(int i = 0; i < medlemsListe.size(); i++) {
            if((medlemsListe.get(i).getMedlNr() == medlNr) && (medlemsListe.get(i).okPassord(passord))) {
                return medlemsListe.get(i).getPoeng();
            }
        }
        return 0;
    }

    public boolean registrerPoeng(int medlNr, int antPoeng) {
        for(int i = 0; i < medlemsListe.size(); i++) {
            if(medlNr == medlemsListe.get(i).getMedlNr()) {
                if (medlemsListe.get(i) instanceof SoelvMedlem) {
                    SoelvMedlem medlem1 = (SoelvMedlem) medlemsListe.get(i);
                    medlem1.registrerPoeng(antPoeng);
                    return true;
                } else if (medlemsListe.get(i) instanceof GullMedlem) {
                    GullMedlem medlem2 = (GullMedlem) medlemsListe.get(i);
                    medlem2.registrerPoeng(antPoeng);
                    return true;
                } else {
                    medlemsListe.get(i).registrerPoeng(antPoeng);
                    return true;
                }
            }
        }
        return false;
    }

    public void nyMedlem(Personalia pers, LocalDate innmeldt) {
        BasicMedlem nyttMedlem  = new BasicMedlem(finnLedigNr(), pers, innmeldt);
        medlemsListe.add(nyttMedlem);
    }

    public boolean sjekkMedlemmer() {
        boolean endretNoen = false;
        for(int i = 0; i < medlemsListe.size(); i++) {
            if(medlemsListe.get(i) instanceof BasicMedlem) {
                BasicMedlem medlem1 = (BasicMedlem) medlemsListe.get(i);
                if(medlem1.getPoeng() >= 75000) {
                    GullMedlem nyttMedlem = new GullMedlem(medlem1.getMedlNr(), medlem1.getPersonalia(), medlem1.getInnmeldtDato(), medlem1.getPoeng());
                    medlemsListe.set(i, nyttMedlem);
                    endretNoen = true;
                } else {
                    if(medlem1.finnKvalPoeng() < 0) {
                        SoelvMedlem nyttMedlem = new SoelvMedlem(medlem1.getMedlNr(), medlem1.getPersonalia(), medlem1.getInnmeldtDato(), medlem1.getPoeng());
                        medlemsListe.set(i, nyttMedlem);
                        endretNoen = true;
                    }
                }
            } else if (medlemsListe.get(i) instanceof SoelvMedlem) {
                SoelvMedlem medlem2 = (SoelvMedlem) medlemsListe.get(i);
                if(medlem2.finnKvalPoeng() < 0) {
                    GullMedlem nyttMedlem = new GullMedlem(medlem2.getMedlNr(), medlem2.getPersonalia(), medlem2.getInnmeldtDato(), medlem2.getPoeng());
                    medlemsListe.set(i, nyttMedlem);
                    endretNoen = true;
                }

            }
        }
        return endretNoen;
    }

    private int finnLedigNr() {
        Random rnd = new Random();
        int medlemsNummer = rnd.nextInt(1000000) + 1;
        boolean gjorOm = true;

        while(gjorOm) {
            boolean ledigNummer = true;
            for(int i = 0; i < medlemsListe.size(); i++) {
                if(medlemsListe.get(i).getMedlNr() == medlemsNummer) {
                    ledigNummer = false;
                }
            }
            if(ledigNummer) {
                medlemsNummer = rnd.nextInt(1000000) + 1;
            } else {
                gjorOm = false;
            }
        }
        return medlemsNummer;
    }
}
