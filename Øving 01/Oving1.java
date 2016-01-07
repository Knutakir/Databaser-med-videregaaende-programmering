import static javax.swing.JOptionPane.*;

class Oving1 {
	public static void main(String[] args){
		Restaurant resturanten;

		String navn = showInputDialog("Resturantnavn: ");
		String aarEtablertLest = showInputDialog("År etablert: ");
		String antBordLest = showInputDialog("Antall bord: ");

		int aarEtablert = Integer.parseInt(aarEtablertLest);
		int antBord = Integer.parseInt(antBordLest);

		resturanten = new Restaurant(navn, aarEtablert, antBord);

		final int reserverBord = 0;
		final int finnBordnr = 1;
		final int frigiBord = 2;
		final int hentAlder = 3;
		final int endreRestaurantNavn = 4;
		final int avslutt = 5;

		String[] muligheter = {"Reserver bord", "Finn hvilke bord en bestemt person har reservert", "Frigi bord", "Hent alder", "Endre restaurantnavn", "Avlsutt"};
		int valg;
		do {

			valg = showOptionDialog(null, "Hei og velkommen!", "Knuts restaurant", YES_NO_OPTION, INFORMATION_MESSAGE, null, muligheter, muligheter[0]);

			switch(valg){
				case reserverBord:
					String personNavn = showInputDialog("Navn: ");
					String antBordRes = showInputDialog("Ant bord: ");
					int antBordReservert = Integer.parseInt(antBordRes);

					int[] bordListe = new int[antBordReservert];
					for(int i = 0; i < antBordReservert; i++){
						String nrBord = showInputDialog("Bordnr: ");
						int nummerBord = Integer.parseInt(nrBord);
						bordListe[i] = nummerBord; // - 1;
					}
					if(resturanten.reserverBord(personNavn, bordListe)){
						showMessageDialog(null, "Bordene er reservert!");
					} else {
						showMessageDialog(null, "Bordene er ikke reservert!");
					}
					break;
				case finnBordnr:
					String personNavnn = showInputDialog("Navn: ");
					int[] tabellBord = resturanten.finnBordReservert(personNavnn);
					String utskrift = personNavnn + " har reservert bordene: ";

					for(int i = 0; i < tabellBord.length; i++){
						//int tmp = tabellBord[i] + 1;
						utskrift += "\n" + tabellBord[i];//tmp;
					}
					showMessageDialog(null, utskrift);
					break;
				case frigiBord:
					String antBordd = showInputDialog("Antall bord som skal frigis: ");
					int antBordFrigis = Integer.parseInt(antBordd);
					int[] bordListee = new int[antBordFrigis];
					int teller = 0;

					for(int i = 0; i < antBordFrigis; i++){
						String bordNr = showInputDialog("Bord nummer: ");
						int bordNumemrr = Integer.parseInt(bordNr);
						bordListee[teller] = bordNumemrr;
						teller++;
					}
					resturanten.frigiBord(bordListee);
					break;

				case hentAlder:
					showMessageDialog(null, "Alderen til restauranten er: " + resturanten.finnAlder());
					break;

				case endreRestaurantNavn:
					String nyttNavn = showInputDialog("Skriv inn nytt navn: ");
					resturanten.setNavn(nyttNavn);
					showMessageDialog(null, "Navnet til restauranten er nå endret.");
					break;

				case avslutt:
					break;
				default:
					break;
			}
		} while (valg != avslutt);
	}
}