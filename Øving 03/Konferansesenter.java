import java.util.ArrayList;

class Konferansesenter{

	private ArrayList<Rom> rommene = new ArrayList<Rom>();

	public boolean registrerRom(int romNummer, int storrelse){//størrelse = antall personer som det er plass til i rommet
		for(int i = 0; i < rommene.size(); i++){
			if(rommene.get(i).getRomNummer() == romNummer){
				return false;
			}
		}
		rommene.add(new Rom(romNummer, storrelse));
		return true;
	}

	public int finnAntRom(){
		return rommene.size();
	}

	public Rom finnBestemtRom(int indeks){ //Finn bestemt rom gitt indeks
		if(indeks >= rommene.size()){
			return null;
		} else {
			return rommene.get(indeks);
		}
	}

	public Rom finnBestemtRomGittNr(int romnr){//finn bestemt rom gitt romnummer
		Rom rommet = null; //samme grunn her
		for(int i = 0; i < rommene.size(); i++){
			if(rommene.get(i).getRomNummer() == romnr){
				rommet = rommene.get(i);
			}
		}
		return rommet;
	}

	public int reserverRom(Reservasjon reservasjonen, int antPers){

		for(int i = 0; i < rommene.size(); i++){
			if(rommene.get(i).getAntPersoner() >= antPers){
				if(rommene.get(i).reserverRom(reservasjonen, antPers)){
					return rommene.get(i).getRomNummer();
				}
			}
		}
		//System.out.println("FEIL");
		return -1;
	}

	public String finnReservasjoner(){
		String tekst = "";
		for(int i = 0; i < rommene.size(); i++){
			tekst += "Rom nummer: " + rommene.get(i).getRomNummer() + "\n" + rommene.get(i).hentReservasjoner();
		}
		return tekst;
	}
}