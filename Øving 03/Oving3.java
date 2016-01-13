//Klientprogram

import static javax.swing.JOptionPane.*;

class Oving3{
	public static void main(String[] args){
		Konferansesenter konfSenter = new Konferansesenter();
		//Registrerer et rom ved start
		if(konfSenter.registrerRom(1, 10)){ //rom nummer 0? eller inkrementere rom nummer isteden for å gjøre det slik?
			showMessageDialog(null, "Et rom er registrert ved start for å gjøre slik at man kan legge til reservasjon med en gang.");
		}

		String[] muligheter = {"Reserver rom", "Registrer nytt rom", "Finn antall rom", "Finn bestemt rom(g.indeks)", "Finn bestemt rom(g.romnummer)", "Avslutt"};
		int valg;
		do {
			valg = showOptionDialog(null, "Hva vil du gjøre?", "Knuts konferansehotell", YES_NO_OPTION, INFORMATION_MESSAGE, null, muligheter, muligheter[0]);

			switch(valg){
				case -1:
					return;
				case 0: //Reserver rom
					Tidspunkt fraTid = new Tidspunkt(Long.parseLong(showInputDialog("Fra tid: ")));//fraTid
					Tidspunkt tilTid = new Tidspunkt(Long.parseLong(showInputDialog("Til tid: ")));//tilTid
					String navn = showInputDialog("Navn: ");
					String tlfNummer = showInputDialog("Telefonnummer: ");
					int antPersoner = Integer.parseInt(showInputDialog("Antall personer: "));

					try{
						int romNummer = konfSenter.reserverRom(new Reservasjon(fraTid, tilTid, new Kunde(navn, tlfNummer)), antPersoner);

						if(romNummer != -1){
							showMessageDialog(null, "Reservasjon er registrert ved rom nummer: " + romNummer + ".");
						} else {
							showMessageDialog(null, "Kunne ikke reservere rom for gitt data.");
						}
					} catch(IllegalArgumentException asd){
						showMessageDialog(null, "Det skjeddde en feil: " + asd.getMessage());
					}
					break;
				case 1: //Registrer nytt rom
					int romNr = Integer.parseInt(showInputDialog("Skriv inn romnummer: "));
					int romStorrelse = Integer.parseInt(showInputDialog("Skriv inn antall personer: "));
					if(konfSenter.registrerRom(romNr, romStorrelse)){
						showMessageDialog(null, "Du har registrert et nytt rom med romnummer: " + romNr + "\n Og antall personer: " + romStorrelse);
					} else {
						showMessageDialog(null, "Du kunne ikke registrere et nytt rom med de veridene som ble skrevet inn.");
					}

					break;
				case 2: //Finn antall rom
					showMessageDialog(null, "Antall rom: " + konfSenter.finnAntRom() + "\n" + konfSenter.finnReservasjoner());
					break;
				case 3: //Finn bestemt rom
					int indeks = Integer.parseInt(showInputDialog("Skriv inn indeks: "));
					showMessageDialog(null, konfSenter.finnBestemtRom(indeks).toString() + "\n" + konfSenter.finnReservasjoner());
					break;

				case 4:
					int bordnummer = Integer.parseInt(showInputDialog("Skriv inn bordnummer: "));
					showMessageDialog(null, konfSenter.finnBestemtRomGittNr(bordnummer).toString());
					break;
				case 5: //Avslutt
					break;
			}
		} while (valg != 5);
	}
}