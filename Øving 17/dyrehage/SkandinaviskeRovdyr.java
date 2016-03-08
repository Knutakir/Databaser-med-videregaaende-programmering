package dyrehage;

/**
 * Created by Knut on 04.03.2016.
 */
public interface SkandinaviskeRovdyr {
    String getNavn();
    int getFDato();
    int getAlder();
    String getAdresse();
    void flytt(String nyAdresse);
    String skrivUtInfo();
    int getAntKull();
    void leggTilKull(int antall);
    void leggTilNyttKull();
}
