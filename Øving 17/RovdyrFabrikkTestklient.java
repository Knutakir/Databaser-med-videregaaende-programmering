import dyrehage.Rovdyrfabrikk;
import dyrehage.SkandinaviskeRovdyr;

/**
 * Created by Knut on 04.03.2016.
 */
public class RovdyrFabrikkTestklient {
    public static void main(String[] args) {
        /*
        String skrivUtInfo();*/
        Rovdyrfabrikk rFabrikk = new Rovdyrfabrikk();

        SkandinaviskeRovdyr ingunn = rFabrikk.nyBinne(20160101,"Bur 1", "Ingunn", 19960822, 10);
        SkandinaviskeRovdyr knut = rFabrikk.nyHannbjørn(20160101, "Bur 1", "Knut", 19961110);

        SkandinaviskeRovdyr asd123 = rFabrikk.nyUlvehann(20101010, "Bur 2", "John", 19901010);
        SkandinaviskeRovdyr asd1234 = rFabrikk.nyUlvetispe(20101010, "Bur 2", "Susanne", 19901010, 20);

        //Test nummer 1:
        if(ingunn.getNavn() == "Ingunn" && ingunn.getAdresse() == "Bur 1") {
            System.out.println("Test 1: OK");
        }

        //Test nummer 2:
        if(knut.getAlder() == 20 && knut.getFDato() == 19961110) {
            System.out.println("Test 2: OK");
        }

        //Test nummer 3:
        String nyAdresse = "Bur 1337";
        asd123.flytt(nyAdresse);
        if(asd123.getAdresse() == nyAdresse) {
            System.out.println("Test 3: OK");
        }

        //Test nummer 4:
        String utskrift = "Norsk navn: Ulv\nLatinsk navn og familie: Canis lupus, Canidae\nAdresse: Bur 2\nNavn: Susanne\nFødselsdato: 19901010\nKjønn: Hunn\nEr farlig: true";
        if(asd1234.skrivUtInfo().equals(utskrift) && asd1234.getAntKull() == 20) {
            System.out.println("Test 4: OK");
        }
    }
}
