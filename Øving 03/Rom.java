import java.util.ArrayList;

class Rom {

	private ArrayList<Reservasjon> reservasjoner = new ArrayList<Reservasjon>();
	private int romNummer;
	private int antPersoner;

	public Rom(int romNummer, int antPersoner){
		this.romNummer = romNummer;
		this.antPersoner = antPersoner; //samme som størrelse
	}

	public int getRomNummer(){
		return romNummer;
	}

	public int getAntPersoner(){
		return antPersoner;
	}

	public boolean reserverRom(Reservasjon reservasjonen, int antPers){
		for(int i = 0; i < reservasjoner.size(); i++){
			if(reservasjoner.get(i).overlapp(reservasjonen.getFraTid(), reservasjonen.getTilTid())){
				return false;
			}
		}
		reservasjoner.add(reservasjonen);
		return true;
	}

	public String toString(){
		return "Romnummer: " + romNummer + " har plass til " + antPersoner + " personer.";
	}

	public String hentReservasjoner(){
		String tekst = "";
		for(int i = 0; i < reservasjoner.size(); i++){
			tekst += reservasjoner.get(i).toString();
		}
		return tekst;
	}

	public static void main(String[] args){
		System.out.println("Test rom-klasse: ");
		Rom r1 = new Rom(8, 10);
		Rom r2 = new Rom(3, 3);
		Rom r3 = new Rom(1, 1);
		Rom r4 = new Rom(9, 5);

		if((r1.toString().equals("Romnummer: 8 har plass til 10 personer.")) && (r2.toString().equals("Romnummer: 3 har plass til 3 personer.")) && (r3.toString().equals("Romnummer: 1 har plass til 1 personer.")) && (r4.toString().equals("Romnummer: 9 har plass til 5 personer."))){
			System.out.println("Test vellykket");
		}
	}
}