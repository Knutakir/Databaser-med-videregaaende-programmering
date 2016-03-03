import java.util.Arrays;
import java.util.Comparator;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Knut on 16.02.2016.
 */
public class TestProgram {
    public static void main(String[] args) {
        Staa staaTribune = new Staa("Ståtribnr1", 100, 500);
        Staa staaTribune2 = new Staa("Ståtribnr2", 75, 380);
        Sitte sitteTribune = new Sitte("Sittetribune4lyf", 50, 1000.0, 10);
        VIP vipTribune = new VIP("VIPisdashiet", 25, 10000.0, 5);
        Tribune[] tribuneliste = {staaTribune, staaTribune2, sitteTribune, vipTribune};

        //Utføre metodene som kjøper billetter:
        String[] test = {"Knut", "Ingunn", "Test", "test2"};
        //Ståtribune:
        Billett[] billettListeStaa = staaTribune.kjopBilletter(10);
        for(Billett b : billettListeStaa) {
            showMessageDialog(null, b.toString());
        }

        //Ståtribune2:
        Billett[] billettListeStaa2 = staaTribune2.kjopBilletter(test);
        for(Billett b : billettListeStaa2) {
            showMessageDialog(null, b.toString());
        }

        //Sittetribune:
        Billett[] sitteListe = sitteTribune.kjopBilletter(5);
        for(Billett b : sitteListe) {
            showMessageDialog(null, b.toString());
        }

        //Viptribune:
        Billett[] vipListe1 = vipTribune.kjopBilletter(test);
        for(Billett b : vipListe1) {
            showMessageDialog(null, b.toString());
        }

        /*Billett[] vipListe2 = vipTribune.kjopBilletter(5);
        for(Billett b : vipListe2) {
            showMessageDialog(null, b.toString());
        }*/

        for(Tribune trib : tribuneliste){
            showMessageDialog(null, trib.toString());
        }

        //Oppgave E:
        //1: Sortering vha en metode i klassen Arrays:
        tribuneliste = sorter(tribuneliste);
    }

    private static Tribune[] sorter(Tribune[] trib) {
        Arrays.sort(trib, new Comparator<Tribune>() {
            @Override
            public int compare(Tribune t1, Tribune t2) {
                if(t1.finnInntekt() > t2.finnInntekt()) {
                    return 1;
                } else if(t1.finnInntekt() < t2.finnInntekt()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return trib;
    }
}
